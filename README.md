# ElCarro
myTest

groups:
"smoke","positives"
"regress","negative"

suites:
car
user


-Ptarget=pre_prod


All Positive tests -chrome
  ./gradlew clean -Pbrowser=chrome -Dsuite=positive myTest

All Negative tests -firefox
  ./gradlew clean -Pbrowser=firefox -Dsuite=negative myTest

/// All Tests (Regress) -chrome
./gradlew clean -Pbrowser=chrome -Dsuite=regress -Dgroups=positive myTest

+ Registration Positive -chrome
  ./gradlew clean -Pbrowser=chrome -Dsuite=reg -Dgroups=positive myTest
+ Registration Negative -Firefox
  ./gradlew clean -Pbrowser=firefox -Dsuite=reg -Dgroups=negative myTest

+ Login Positive -Firefox
  ./gradlew clean -Pbrowser=firefox -Dsuite=login -Dgroups=positive myTest
+ Login Negative -Chrome
  ./gradlew clean -Pbrowser=chrome -Dsuite=login -Dgroups=negative myTest

+ Add car  Tests
  ./gradlew clean -Pbrowser=chrome -Dsuite=addcar myTest

+ Search car  Tests
  ./gradlew clean -Pbrowser=chrome -Dsuite=searchcar -Dgroups=positive myTest

