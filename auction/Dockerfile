# Node.js 이미지를 기반으로 합니다.
FROM node:14

# 애플리케이션 디렉토리를 생성합니다.
WORKDIR /usr/src/app

# 애플리케이션 의존성 설치
# package.json 복사
COPY package.json ./

# npm install과 rtcmulticonnection 설치를 동시에 실행합니다.
RUN npm install && npm install rtcmulticonnection

# 앱 소스 추가
COPY . .

# 서버를 실행합니다.
CMD [ "node", "server.js" ]

# 애플리케이션이 3002 포트에서 실행되도록 합니다.
EXPOSE 9001
