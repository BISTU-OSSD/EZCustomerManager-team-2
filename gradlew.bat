@if "%DEBUG%" == "" @echo off
@rem ######## Gradle Wrapper 启动脚本 ########

set GRADLE_VERSION=8.5

if not exist "./gradle/wrapper" mkdir "./gradle/wrapper"

if not exist "./gradle/wrapper/gradle-wrapper.jar" (
    echo 正在下载 gradle-wrapper.jar...
    powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/gradle/gradle/v%GRADLE_VERSION%/gradle/wrapper/gradle-wrapper.jar' -OutFile './gradle/wrapper/gradle-wrapper.jar'"
)

if not exist "./gradle/wrapper/gradle-wrapper.properties" (
    echo distributionBase=GRADLE_USER_HOME> ./gradle/wrapper/gradle-wrapper.properties
    echo distributionPath=wrapper/dists>> ./gradle/wrapper/gradle-wrapper.properties
    echo distributionUrl=https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip>> ./gradle/wrapper/gradle-wrapper.properties
    echo zipStoreBase=GRADLE_USER_HOME>> ./gradle/wrapper/gradle-wrapper.properties
    echo zipStorePath=wrapper/dists>> ./gradle/wrapper/gradle-wrapper.properties
)

java -cp "./gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
