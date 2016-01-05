/* Person */
INSERT INTO `Person`(`idPerson`,`birthdate`,`email`,`firstname`,`fiscalcode`,`gender`,`lastname`,`idDoctor`) 
VALUES ('1','273535200000',NULL,'Bob',NULL,'M','Smith','1'),
('2','273535200000',NULL,'Lucio',NULL,'M','Dalla','1'),
('3','273535200000',NULL,'Kate',NULL,'F','Moss','2'),
('4','273535200000',NULL,'Lenny',NULL,'M','Kravitz','2'),
('5','273535200000',NULL,'Yara',NULL,'F','Luviza','3');

/* Doctor */
INSERT INTO `Doctor`(`idDoctor`,`city`,`firstname`,`lastname`,`specialization`) VALUES ('1','Trento','Mario','Rossi','Nutritionist'),
('2','Bolzano','Valerio','Bianchi','GP'),
('3','Udine','Giuseppe','Verdi','Nutritionist');

/* Family */
INSERT INTO `Family`(`idFamily`,`firstname`,`lastname`,`role`,`idPerson`) VALUES ('1','Jack','Smith','Father','1'),
('2','Micheal','Dalla','Father','2'),
('3','Jack','Moss','Father','3'),
('4','Milly','Kravitz','Mother','4'),
('5','Jack','Luviza','Father','5');

/* MeasureDefinition */
INSERT INTO `MeasureDefinition`(`idMeasureDef`,`alarmLevel`,`endValue`,`measureName`,`measureType`,`startValue`) VALUES 
('1',NULL,'180','weight','double','40'),
('2',NULL,'220','height','double','120'),
('3',NULL,'100000','steps','integer','0'),
('4',NULL,'90','blood pressure min','double','70'),
('5',NULL,'130','blood pressure max','double','110'),
('6',NULL,'90','heart rate','integer','50'),
('7',NULL,'25','bmi','double','18');


/* Measure */
INSERT INTO `Measure`(`idMeasure`,`timestamp`,`value`,`idPerson`,`idMeasureDef`) VALUES ('1','1451602800000','87','1','1'),
('2','1449874800000','85','1','4'),
('3','1449874800000','135','1','5'),
('4','1446332400000','90','2','1'),
('5','1451689200000','190','2','2'),
('6','1451862000000','60','3','6'),
('7','1449874800000','20','3','7'),
('8','1449874800000','80','4','4'),
('9','1452380400000','120','4','5'),
('10','1446332400000','120','5','1');

/* Target */
INSERT INTO `Target`(`idTarget`,`achieved`,`conditionTarget`,`endDateTarget`,`startDateTarget`,`value`,`idMeasureDef`,`idPerson`) VALUES ('1',NULL,'<','1456786800000','1441058400000','75','1','1'),
('2','0','<','1446332400000','1441058400000','70','1','5');

/* Reminder */
INSERT INTO `Reminder`(`idReminder`,`autocreate`,`createReminder`,`expireReminder`,`relevanceLevel`,`text`,`idPerson`) VALUES ('1','0','1451602800000','1456786800000','5','Remember to check your weight','1'),
('2','1','1441058400000','1450047600000','8','Remember to check your blood pressure all days','4');

/*
Target
Marzo 2016 ---> "1456786800000"
Settembre 2015 ---> "1441058400000"
5/01/2016 ---> "1451948400000"
1/09/2015 ---> "1441058400000"



Gennaio - Marzo 2016 
"1451602800000"
"1456786800000"

Settembre - Dicembre 2015
"1441058400000"
"1450047600000"



"1451602800000"
"1449874800000"
"1446332400000"
"1451689200000"
"1448924400000"
"1451862000000"
"1452380400000"

*/