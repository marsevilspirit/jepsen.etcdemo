FROM debian:bullseye AS builder

RUN apt-get update && apt-get install -y build-essential make pkg-config wget unzip

RUN wget https://github.com/redis/redis/archive/refs/tags/7.4.1.zip && \
    unzip 7.4.1.zip && rm -rf 7.4.1.zip && cd redis-7.4.1 && make

FROM debian:bullseye

# 安装所需的软件包
RUN apt-get update && \
    apt-get install -y \
        openjdk-11-jdk \
        libjna-java \
        git \
        gnuplot \
        wget \
        vim \
        graphviz \
        build-essential \
        autoconf \
        zlib1g-dev \
        curl

ENV LEIN_ROOT true
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
    mv lein /usr/bin && \
    chmod +x /usr/bin/lein && \
    lein self-install

ADD entrypoint.sh /entrypoint.sh
RUN chmod 0755 /entrypoint.sh

EXPOSE 8080
ENTRYPOINT ["/entrypoint.sh"]
