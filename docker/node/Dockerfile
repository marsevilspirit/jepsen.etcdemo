FROM debian:buster AS builder

RUN apt-get -y -q update && apt-get install -y build-essential make pkg-config wget unzip

FROM debian:buster

# Install packages
# RUN sed -i "s@/deb.debian.org/@/mirrors.tuna.tsinghua.edu.cn/@g" /etc/apt/sources.list  # 如果需要使用国内镜像源，可以取消注释这行
RUN apt-get update && \
    apt-get -y install \
        openssh-server \
        pwgen \
        && \
mkdir -p /var/run/sshd && \
sed -i "s/UsePrivilegeSeparation.*/UsePrivilegeSeparation no/g" /etc/ssh/sshd_config && \
sed -i "s/PermitRootLogin prohibit-password/PermitRootLogin yes/g" /etc/ssh/sshd_config && \
echo "PubkeyAcceptedKeyTypes=+ssh-rsa" >> /etc/ssh/sshd_config

# Install Jepsen deps
RUN apt-get update && \
    apt-get -y install \
        apt-transport-https \
        software-properties-common \
        build-essential \
        bzip2 \
        curl \
        faketime \
        iproute2 \
        iptables \
        iputils-ping \
        libzip4 \
        logrotate \
        man \
        man-db \
        net-tools \
        ntpdate \
        psmisc \
        python3 \
        rsyslog \
        sudo \
        tar \
        unzip \
        vim \
        wget \
        tcpdump \
        git \
        cmake \
        automake \
        autoconf \
        libtool \
        libssl-dev \
        zlib1g-dev \
        && \
        apt-get remove -y --purge --auto-remove systemd

ADD entrypoint.sh /entrypoint.sh
RUN chmod 0755 /entrypoint.sh

EXPOSE 22
ENTRYPOINT ["/entrypoint.sh"]
