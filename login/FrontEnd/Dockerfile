FROM node:latest
WORKDIR /

# Install dependencies based on the preferred package manager
COPY ./ ./

RUN npm install 
RUN npm run build

COPY --from=builder /.next/standalone ./
COPY --from=builder /.next/static ./.next/static

EXPOSE 3000

CMD ["node", "server.js"]