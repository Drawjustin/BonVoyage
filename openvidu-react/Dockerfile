# Node.js 이미지를 기반으로 설정
FROM node:14 AS build-stage

# 작업 디렉토리 설정
WORKDIR /app

# 앱 의존성 설치
COPY package*.json ./
RUN npm install

# 앱 소스 복사
COPY . .

# 앱 빌드
RUN npm run build

# Nginx를 사용하여 빌드된 앱 제공
FROM nginx:stable-alpine as production-stage

# 빌드 단계에서 생성된 빌드 파일을 Nginx의 서빙 디렉토리로 복사
COPY --from=build-stage /app/build /usr/share/nginx/html

# 5000 포트를 외부에 노출
EXPOSE 5000

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
