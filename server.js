// http://127.0.0.1:9001
// http://localhost:9001

const fs = require('fs');
const path = require('path');
const url = require('url');
var httpServer = require('http');

const ioServer = require('socket.io');
const RTCMultiConnectionServer = require('rtcmulticonnection-server');

var PORT = 9001;
var isUseHTTPs = false;

const jsonPath = {
    config: 'config.json',
    logs: 'logs.json'
};

const BASH_COLORS_HELPER = RTCMultiConnectionServer.BASH_COLORS_HELPER;
const getValuesFromConfigJson = RTCMultiConnectionServer.getValuesFromConfigJson;
const getBashParameters = RTCMultiConnectionServer.getBashParameters;
const resolveURL = RTCMultiConnectionServer.resolveURL;

var config = getValuesFromConfigJson(jsonPath);
config = getBashParameters(config, BASH_COLORS_HELPER);

// if user didn't modifed "PORT" object
// then read value from "config.json"
if(PORT === 9001) {
    PORT = config.port;
}
if(isUseHTTPs === false) {
    isUseHTTPs = config.isUseHTTPs;
}

function serverHandler(request, response) {
    // to make sure we always get valid info from json file
    // even if external codes are overriding it
    config = getValuesFromConfigJson(jsonPath);
    config = getBashParameters(config, BASH_COLORS_HELPER);


    var handleUnexpectedError = function(e) {
        // pushLogs(config, 'Unexpected', e);

        if (!response.finished) {
            response.writeHead(404, {
                'Content-Type': 'text/plain'
            });
            response.write('404 Not Found: Unexpected error.\n' + e.message + '\n\n' + e.stack);
            response.end();
        }
    };

    // HTTP_GET handling code goes below
    try {
        var uri, filename;

        try {
            if (!config.dirPath || !config.dirPath.length) {
                config.dirPath = null;
            }

            uri = url.parse(request.url).pathname;
            filename = path.join(config.dirPath ? resolveURL(config.dirPath) : process.cwd(), uri);
        } catch (e) {
            pushLogs(config, 'url.parse', e);
        }

        filename = (filename || '').toString();

        if (request.method !== 'GET' || uri.indexOf('..') !== -1) {
            try {
                response.writeHead(401, {
                    'Content-Type': 'text/plain'
                });
                response.write('401 Unauthorized: ' + path.join('/', uri) + '\n');
                response.end();
                return;
            } catch (e) {
                pushLogs(config, '!GET or ..', e);
            }
        }

        if(filename.indexOf(resolveURL('/admin/')) !== -1 && config.enableAdmin !== true) {
            try {
                response.writeHead(401, {
                    'Content-Type': 'text/plain'
                });
                response.write('401 Unauthorized: ' + path.join('/', uri) + '\n');
                response.end();
                return;
            } catch (e) {
                pushLogs(config, '!GET or ..', e);
            }
            return;
        }

        var matched = false;
        ['/demos/', '/dev/', '/dist/', '/socket.io/', '/node_modules/canvas-designer/', '/admin/'].forEach(function(item) {
            if (filename.indexOf(resolveURL(item)) !== -1) {
                matched = true;
            }
        });

        // files from node_modules
        ['RecordRTC.js', 'FileBufferReader.js', 'getStats.js', 'getScreenId.js', 'adapter.js', 'MultiStreamsMixer.js'].forEach(function(item) {
            if (filename.indexOf(resolveURL('/node_modules/')) !== -1 && filename.indexOf(resolveURL(item)) !== -1) {
                matched = true;
            }
        });

        if (filename.search(/.js|.json/g) !== -1 && !matched) {
            try {
                response.writeHead(404, {
                    'Content-Type': 'text/plain'
                });
                response.write('404 Not Found: ' + path.join('/', uri) + '\n');
                response.end();
                return;
            } catch (e) {
                pushLogs(config, '404 Not Found', e);
            }
        }

        ['Video-Broadcasting', 'Screen-Sharing', 'Switch-Cameras'].forEach(function(fname) {
            try {
                if (filename.indexOf(fname + '.html') !== -1) {
                    filename = filename.replace(fname + '.html', fname.toLowerCase() + '.html');
                }
            } catch (e) {
                pushLogs(config, 'forEach', e);
            }
        });

        var stats;

        try {
            stats = fs.lstatSync(filename);

            if (filename.search(/demos/g) === -1 && filename.search(/admin/g) === -1 && stats.isDirectory() && config.homePage === '/demos/index.html') {
                if (response.redirect) {
                    response.redirect('/demos/');
                } else {
                    response.writeHead(301, {
                        'Location': '/demos/'
                    });
                }
                response.end();
                return;
            }
        } catch (e) {
            response.writeHead(404, {
                'Content-Type': 'text/plain'
            });
            response.write('404 Not Found: ' + path.join('/', uri) + '\n');
            response.end();
            return;
        }

        try {
            if (fs.statSync(filename).isDirectory()) {
                const directoryToFile = {
                    '/demos/MultiRTC/': '/demos/MultiRTC/index.html',
                    '/admin/': '/admin/index.html',
                    '/demos/dashboard/': '/demos/dashboard/index.html',
                    '/demos/video-conference/': '/demos/video-conference/index.html',
                    '/demos': '/demos/index.html'
                };
        
                let matched = false;
                for (let directory in directoryToFile) {
                    if (filename.indexOf(resolveURL(directory)) !== -1) {
                        filename = filename.replace(resolveURL(directory), '');
                        filename += resolveURL(directoryToFile[directory]);
                        matched = true;
                        break;
                    }
                }
        
                if (!matched) {
                    filename += resolveURL(config.homePage);
                }
            }
        } catch (e) {
            handleUnexpectedError(e, 404);
        }        

        var contentType = 'text/plain';
        if (filename.toLowerCase().indexOf('.html') !== -1) {
            contentType = 'text/html';
        }
        if (filename.toLowerCase().indexOf('.css') !== -1) {
            contentType = 'text/css';
        }
        if (filename.toLowerCase().indexOf('.png') !== -1) {
            contentType = 'image/png';
        }

        fs.readFile(filename, 'binary', function(err, file) {
            if (err) {
                handleUnexpectedError(err, 500);
                return;
            }
    
            try {
                file = file.replace('connection.socketURL = \'/\';', 'connection.socketURL = \'' + config.socketURL + '\';');
            } catch (e) {
                handleUnexpectedError(e, 404);
                return;
            }
    
            if (!response.finished) {
                response.writeHead(200, {
                    'Content-Type': contentType
                });
                response.write(file, 'binary');
                response.end();
            }
        });
    } catch (e) {
        handleUnexpectedError(e, 404);
    }
}

var httpApp;

if (isUseHTTPs) {
    httpServer = require('https');

    // See how to use a valid certificate:
    // https://github.com/muaz-khan/WebRTC-Experiment/issues/62
    var options = {
        key: null,
        cert: null,
        ca: null
    };

    var pfx = false;

    if (!fs.existsSync(config.sslKey)) {
        console.log(BASH_COLORS_HELPER.getRedFG(), 'sslKey:\t ' + config.sslKey + ' does not exist.');
    } else {
        pfx = config.sslKey.indexOf('.pfx') !== -1;
        options.key = fs.readFileSync(config.sslKey);
    }

    if (!fs.existsSync(config.sslCert)) {
        console.log(BASH_COLORS_HELPER.getRedFG(), 'sslCert:\t ' + config.sslCert + ' does not exist.');
    } else {
        options.cert = fs.readFileSync(config.sslCert);
    }

    if (config.sslCabundle) {
        if (!fs.existsSync(config.sslCabundle)) {
            console.log(BASH_COLORS_HELPER.getRedFG(), 'sslCabundle:\t ' + config.sslCabundle + ' does not exist.');
        }

        options.ca = fs.readFileSync(config.sslCabundle);
    }

    if (pfx === true) {
        options = {
            pfx: sslKey
        };
    }

    httpApp = httpServer.createServer(options, serverHandler);
} else {
    httpApp = httpServer.createServer(serverHandler);
}

RTCMultiConnectionServer.beforeHttpListen(httpApp, config);
httpApp = httpApp.listen(process.env.PORT || PORT, process.env.IP || "0.0.0.0", function() {
    RTCMultiConnectionServer.afterHttpListen(httpApp, config);
});

// 세션을 종료하고 사용자를 내보내는 함수
function endSession(sessionId) {
    var socket = io.sockets.connected[sessionId];
    if (socket) {
        // 'session end' 이벤트를 클라이언트에게 전송
        socket.broadcast.emit(params.socketCustomEvent, { message: 'session end'});
        socket.disconnect();
    }
}

// --------------------------
// socket.io codes goes below
let bidPriceTime = 3000;
let aucSessionTime = 10; //원래 180임
let intervalId;
let timeLeft = aucSessionTime;
ioServer(httpApp).on('connection', function(socket) {
    RTCMultiConnectionServer.addSocket(socket, config);

    // ----------------------
    // below code is optional

    const params = socket.handshake.query;

    if (!params.socketCustomEvent) {
        params.socketCustomEvent = 'custom-message';
    }

    socket.on(params.socketCustomEvent, function(message) {
        if (message.message === 'bidBtn clicked') {
            // 기존의 interval을 제거하고 새 interval 설정
            clearInterval(intervalId);
            timeLeft = aucSessionTime;
            intervalId = setInterval(() => {
                timeLeft--;
                // 모든 클라이언트에게 남은 시간을 보냄
                socket.broadcast.emit(params.socketCustomEvent, { message: 'tick', timeLeft: timeLeft });
                if (timeLeft <= 0) {
                    clearInterval(intervalId);
                    // 모든 클라이언트에게 경매 종료 메시지를 보냄
                    socket.broadcast.emit(params.socketCustomEvent, { message: 'auction ended' });
                    setTimeout(() => {
                        socket.broadcast.emit(params.socketCustomEvent, { message: 'session end'});
                        socket.disconnect();
                    }, bidPriceTime);
                }
            }, 1000);
        }
        socket.broadcast.emit(params.socketCustomEvent, message);
    });
});
