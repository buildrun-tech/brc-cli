# Generating the executable file

## For building the project with GraalVM Native

1° - Install GraalVM Java 21 - https://www.graalvm.org/downloads/

2° - Run the following command (it will take a couple of minutes)
```
./mvnw -Pnative native:compile -DskipTests=true
```

3° - Find the executable at `/target` folder

## GraalVM running common issues

## Missing packages

- zlib1g-dev

```
sudo apt install zlib1g-dev
```



