FROM node:latest
WORKDIR /

# Install dependencies based on the preferred package manager
COPY ./ ./

RUN npm install 
RUN npm run build

COPY ./.next/standalone ./
COPY ./.next/static ./.next/static

ENV NODE_ENV=production

EXPOSE 3000

CMD ["node", "server.js"]