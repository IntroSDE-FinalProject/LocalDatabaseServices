# lab09-server-heroku

#### To deploy on Heroku:
```bash
heroku login
heroku create --stack cedar --buildpack https://github.com/IntroSDE/heroku-buildpack-ant.git
git push heroku master
heroku open
```

#### Check WSDL, open in your browser:
```bash
https://{YOUR_APP_NAME}.herokuapp.com/ws/people?wsdl
```
#### Test it works via POSTMAN:
```bash
POST /ws/people HTTP/1.1
Host: {YOUR_APP_NAME}.herokuapp.com
Accept: text/xml
Content-Type: text/xml
Cache-Control: no-cache

<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
  <soap:Body xmlns:m="http://ws.document.introsde/">
  <m:getPeopleList>
  </m:getPeopleList>
</soap:Body>
</soap:Envelope>
```

#### Expected response (might contain different / more / less people):
```xml
<?xml version='1.0' encoding='UTF-8'?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:getPeopleListResponse xmlns:ns2="http://ws.document.introsde/">
            <people>
                <birthdate>1978-09-01T22:00:00Z</birthdate>
                <email>chuck.norris@gmail.com</email>
                <idPerson>1</idPerson>
                <lastname>Chan</lastname>
                <Measurements>
                    <lifeStatus>
                        <idMeasure>1</idMeasure>
                        <measureDefinition>
                            <idMeasureDef>1</idMeasureDef>
                            <measureName>weight</measureName>
                            <measureType>double</measureType>
                        </measureDefinition>
                        <value>72.3</value>
                    </lifeStatus>
                    <lifeStatus>
                        <idMeasure>352</idMeasure>
                        <measureDefinition>
                            <idMeasureDef>1</idMeasureDef>
                            <measureName>weight</measureName>
                            <measureType>double</measureType>
                        </measureDefinition>
                        <value>86</value>
                    </lifeStatus>
                </Measurements>
                <name>Jecky</name>
                <username>chuck.norris</username>
            </people>
            <people>
                <birthdate>1978-09-01T22:00:00Z</birthdate>
                <email>pavel.kucherbaev@gmail.com</email>
                <idPerson>2</idPerson>
                <lastname>Norris</lastname>
                <Measurements/>
                <name>Chuck</name>
                <username>pinco</username>
            </people>
        </ns2:getPeopleListResponse>
    </S:Body>
</S:Envelope>
```
