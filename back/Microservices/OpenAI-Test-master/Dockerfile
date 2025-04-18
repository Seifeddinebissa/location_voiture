FROM node:18-alpine
WORKDIR /OpenAI-Test-master
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 5000
CMD ["node", "index.js"]