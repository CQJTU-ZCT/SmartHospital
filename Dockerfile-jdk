FROM ubuntu:16.04
MAINTAINER zjh  <zjhfyq@vip.qq.com>

WORKDIR /usr/local/java

ENV JAVA_HOME=/usr/local/java/jdk1.8.0_161
ENV CLASSPATH=.:${JAVA_HOME}/lib:${JAVA_HOME}/jre/lib:$CLASSPATH \
    JRE_HOME=${JAVA_HOME}/jre \
    PATH=${JAVA_HOME}/bin:${JAVA_HOME}/bin:$PATH

RUN cp /etc/apt/sources.list /etc/apt/sources.list.bak  \
    && echo "# deb cdrom:[Ubuntu 16.04 LTS _Xenial Xerus_ - Release amd64 (20160420.1)]/ xenial main restricted " >>/etc/apt/sources.list \
	&& echo "deb-src http://archive.ubuntu.com/ubuntu xenial main restricted #Added by software-properties" >>/etc/apt/sources.list\
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted" >>/etc/apt/sources.list \
	&& echo "deb-src http://mirrors.aliyun.com/ubuntu/ xenial main restricted multiverse universe #Added by software-properties" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted" >>/etc/apt/sources.list \
	&& echo "deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted multiverse universe #Added by software-properties" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial universe" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-updates universe" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial multiverse" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-updates multiverse" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse" >>/etc/apt/sources.list \
	&& echo "deb-src http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse #Added by software-properties" >>/etc/apt/sources.list \
	&& echo "deb http://archive.canonical.com/ubuntu xenial partner" >>/etc/apt/sources.list \
	&& echo "deb-src http://archive.canonical.com/ubuntu xenial partner" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted" >>/etc/apt/sources.list \
	&& echo "deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted multiverse universe #Added by software-properties" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-security universe" >>/etc/apt/sources.list \
	&& echo "deb http://mirrors.aliyun.com/ubuntu/ xenial-security multiverse" >>/etc/apt/sources.list \
	&& echo "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu xenial stable" >>/etc/apt/sources.list \
	&& echo "# deb-src [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu xenial stable" >>  /etc/apt/sources.list \
	&& apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 7EA0A9C3F273FCD8 \
    && apt-get  update \
    && apt install -y --no-install-recommends  wget \
    && wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u161-b12/2f38c3b165be4555a1fa6e98c45e0808/jdk-8u161-linux-x64.tar.gz  \
    && tar -xvf jdk-8u161-linux-x64.tar.gz  \
    && rm jdk-8u161-linux-x64.tar.gz \
    && rm -rf /var/lib/apt/lists