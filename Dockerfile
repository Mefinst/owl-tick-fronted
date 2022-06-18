FROM node:16.13 AS build
COPY . .
RUN rm package-lock.json
RUN npm install
RUN npm run build --production



FROM alpine:latest AS production
COPY --from=build ./dist /www/public

RUN apk add --update nginx && rm -rf /var/cache/apk/*
COPY config/nginx.conf /etc/nginx/nginx.conf
COPY config/vhost.conf /etc/nginx/conf.d/vhost.conf

# forward request and error logs to docker log collector
RUN ln -svf /dev/stdout /var/log/nginx/access.log \
    && ln -svf /dev/stderr /var/log/nginx/e rror.log

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]