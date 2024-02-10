# Node.js를 사용하여 소스를 빌드하기 위한 단계
FROM node:latest as build

WORKDIR /

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Nginx를 사용하여 빌드된 소스를 호스팅하기 위한 단계
FROM nginx:latest

# Nginx 설정 파일을 복사합니다
COPY default.conf /etc/nginx/conf.d/default.conf

# 빌드된 소스를 Nginx의 HTML 폴더로 복사합니다
COPY --from=build /.next /usr/share/nginx/html

# Nginx를 시작할 때 포트를 노출합니다
EXPOSE 3000

# Nginx를 실행합니다
CMD [ "npm", "run", "start" ]


