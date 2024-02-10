const withImages = require('next-images');
module.exports = withImages({
    images: {
        disableStaticImages: true,
        domains:[
            'res.cloudinary.com',
            'via.placeholder.com'
        ]
    },
    target: 'server',
});