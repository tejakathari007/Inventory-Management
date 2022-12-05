FROM nginx
COPY /dist /usr/share/nginx/html
RUN chmod 777 /usr/share/nginx/html
RUN rm -rf /etc/nginx/conf.d/*
COPY nexops.conf /etc/nginx/conf.d/nexops.conf
