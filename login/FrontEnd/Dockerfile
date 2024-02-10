FROM nginx
RUN apt-get update
RUN apt-get install npm -y

# 웹서버 설정파일 복사
# 프로젝트 루트 밑에 docker폴더, default.conf 생성
COPY default.conf /etc/nginx/conf.d/default.conf
WORKDIR /usr/share/nginx/html

COPY ./package*.json ./
# 모듈 설치
RUN npm install
# 소스 복사
COPY ./ ./

ENV NODE_ENV production

# 빌드
RUN npm run build

# 3000번 포트로 맞춤
EXPOSE 3000

# 컨테이너 실행시 시작
CMD [ "npm", "run", "start" ]