const withImages = require('next-images');
const path = require('path')

module.exports = withImages({
    sassOptions: {
        includePaths: [path.join(__dirname, 'styles')]
    },
    images: {
        disableStaticImages: true,
        domains:[
            'res.cloudinary.com',
            'via.placeholder.com'
        ]
    }
});