FROM onsdigital/java-component

# Phantom.js
WORKDIR /usr/phantom
RUN apt-get install -y tar bzip2
ADD https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-1.9.8-linux-x86_64.tar.bz2 /usr/phantom/phantom.tar.bz2
RUN tar -xvjf phantom.tar.bz2
RUN mv phantomjs-1.9.8-linux-x86_64/bin/phantomjs /usr/local/bin/

RUN pwd

WORKDIR /home/ec2-user

RUN mkdir -p docker_accessibility

COPY ./lib /home/ec2-user/docker_accessibility/lib

#RUN sudo wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/2.33/chromedriver_linux64.zip && sudo unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/

#RUN apk add --update bash ttf-dejavu fontconfig

#COPY phantomjs-2.1.1-linux-x86_64.tar.bz2 /home/ec2-user/docker_accessibility/phantomjs-2.1.1-linux-x86_64.tar.bz2

WORKDIR /home/ec2-user/docker_accessibility

#RUN tar xvjf phantomjs-2.1.1-linux-x86_64.tar.bz2

#WORKDIR /home/ec2-user/docker_accessibility/phantomjs-2.1.1-linux-x86_64/bin

#RUN ls

#RUN pwd

#RUN  chmod +x phantomjs

#RUN pwd

#RUN ls

COPY datafile.properties /home/ec2-user/docker_accessibility/datafile.properties

WORKDIR /home/ec2-user/docker_accessibility/lib

ENV export CLASSPATH=/home/ec2-user/docker_accessibility/lib/axe-selenium-2.0.jar:/home/ec2-user/docker_accessibility/lib/com.docker_parsing.jar:/home/ec2-user/docker_accessibility/lib/java-json.jar:/home/ec2-user/docker_accessibility/lib/jsch-0.1.54.jar:/home/ec2-user/docker_accessibility/lib/json-simple-1.1.jar:/home/ec2-user/docker_accessibility/lib/org.eclipse.jgit-3.4.0.201406041058-rc3.jar:/home/ec2-user/docker_accessibility/lib/selenium-java-2.45.0.jar:/home/ec2-user/docker_accessibility/lib/selenium-server-standalone-3.4.0.jar:

RUN javac -cp "./*:" Demo.java

CMD java -cp "./*:" Demo
