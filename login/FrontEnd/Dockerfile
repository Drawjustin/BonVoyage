FROM nginx:latest
# 웹서버 설정파일 복사
# 프로젝트 루트 밑에 docker폴더, default.conf 생성
COPY default.conf /etc/nginx/conf.d/default.conf

WORKDIR /usr/share/nginx/html

COPY ./package*.json ./

# Install dependencies
RUN npm install
# 소스 복사
COPY ./ ./

RUN rm -rf /etc/nginx/conf.d


# 빌드
RUN npm run build
# Copy the custom nginx.conf file to the nginx configuration directory

COPY default.conf /etc/nginx/conf.d/default.conf
# Copy the build output from the build environment to the nginx directory
COPY --from=build /.next /usr/share/nginx/html

# 3000번 포트로 맞춤
# Expose port 80
EXPOSE 3000

# 컨테이너 실행시 시작
CMD [ "npm", "run", "start" ]

