-- 
--  Table structure for fish
-- 
CREATE TABLE IF NOT EXISTS `fish` (
  `id` int(5) NOT NULL default '0',
  `level` int(5) NOT NULL default '0',
  `name` varchar(40) NOT NULL default '',
  `hp` int(5) NOT NULL default '0',
  `hpregen` int(3) NOT NULL default '5',
  `fish_type` int(1) NOT NULL default '0', -- 0 = wide, 1 = swift, 2 = ugly, 3 = box, 4 = easy_wide, 5 = easy_swift, 6 = easy_ugly, 7 = hard_wide, 8 = hard_swift, 9 = hard_ugly
  `fish_group` int(1) NOT NULL default '0', -- 0 = easy, 1 = normal, 2 = hard
  `fish_guts` INT(4) NOT NULL default '0',
  `guts_check_time` INT(4) NOT NULL default '0',
  `wait_time` INT(5) NOT NULL default '0',
  `combat_time` INT(5) NOT NULL default '0',
  PRIMARY KEY  (`id`,`level`)
) TYPE=MyISAM;
-- 
--  Records for table fish
-- 

INSERT INTO `fish` VALUES
(6411,1,'Small Green Nimble Fish',100,4,1,1,500,5000,20000,24000),
(6412,1,'Small Green Ugly Fish',116,4,2,1,502,5000,20000,27000),
(6413,1,'Small Green Pudgy Fish',132,4,0,1,504,5000,20000,31000),
(6414,2,'Green Nimble Fish',150,6,1,1,506,5000,20000,25000),
(6415,2,'Green Ugly Fish',168,6,2,1,508,5000,20000,28000),
(6416,2,'Green Pudgy Fish',188,6,0,1,510,5000,20000,31000),
(6417,3,'Large Green Nimble Fish',208,8,1,1,512,5000,20000,26000),
(6418,3,'Large Green Ugly Fish',230,8,2,1,514,5000,20000,29000),
(6419,3,'Large Green Pudgy Fish',252,8,0,1,516,5000,20000,32000),
(6420,4,'Small Jade Nimble Fish',276,10,1,1,518,5000,20000,28000),
(6421,4,'Small Jade Ugly Fish',300,10,2,1,520,5000,20000,31000),
(6422,4,'Small Jade Fat Fish',310,10,0,1,522,5000,20000,32000),
(6423,5,'Jade Nimble Fish',320,11,1,1,524,5000,20000,30000),
(6424,5,'Jade Ugly Fish',330,11,2,1,526,4000,20000,31000),
(6425,5,'Jade Fat Fish',340,11,0,1,528,4000,20000,32000),
(6426,6,'Big Jade Nimble Fish',350,12,1,1,530,4000,20000,30000),
(6427,6,'Big Jade Ugly Fish',360,12,2,1,532,4000,20000,31000),
(6428,6,'Big Jade Fat Fish',370,12,0,1,534,4000,20000,32000),
(6429,7,'Small Blue Nimble Fish',380,12,1,1,536,4000,20000,31000),
(6430,7,'Small Blue Ugly Fish',390,13,2,1,538,4000,20000,31000),
(6431,7,'Small Blue Fat Fish',400,13,0,1,540,4000,20000,32000),
(6432,8,'Blue Nimble Fish',410,13,1,1,542,4000,20000,31000),
(6433,8,'Blue Ugly Fish',420,13,2,1,544,4000,20000,32000),
(6434,8,'Blue Fat Fish',430,14,0,1,546,4000,20000,31000),
(6435,9,'Big Blue Nimble Fish',506,17,1,1,548,4000,20000,29000),
(6436,9,'Big Blue Ugly Fish',518,17,2,1,550,4000,20000,30000),
(6437,9,'Big Blue Fat Fish',529,17,0,1,552,4000,20000,31000),
(6438,10,'Small Yellow Nimble Fish',541,18,1,1,554,4000,20000,30000),
(6439,10,'Small Yellow Ugly Fish',552,18,2,1,556,4000,20000,30000),
(6440,10,'Small Yellow Fat Fish',564,18,0,1,558,4000,20000,31000),
(6441,11,'Yellow Nimble Fish',575,19,1,1,560,4000,20000,30000),
(6442,11,'Yellow Ugly Fish',587,19,2,1,562,4000,20000,31000),
(6443,11,'Yellow Fat Fish',598,19,0,1,564,4000,20000,31000),
(6444,12,'Big Yellow Nimble Fish',610,20,1,1,566,4000,20000,30000),
(6445,12,'Big Yellow Ugly Fish',621,20,2,1,568,4000,20000,31000),
(6446,12,'Big Yellow Fat Fish',633,20,0,1,570,4000,20000,31000),
(6447,13,'Small Orange Nimble Fish',644,21,1,1,572,4000,20000,30000),
(6448,13,'Small Orange Ugly Fish',656,21,2,1,574,4000,20000,31000),
(6449,13,'Small Orange Fat Fish',667,21,0,1,576,3000,20000,32000),
(6450,14,'Orange Nimble Fish',679,22,1,1,578,3000,20000,31000),
(6451,14,'Orange Ugly Fish',690,22,2,1,580,3000,20000,31000),
(6452,14,'Orange Fat Fish',702,22,0,1,582,3000,20000,32000),
(6453,15,'Big Orange Nimble Fish',713,25,1,1,584,3000,20000,29000),
(6454,15,'Big Orange Ugly Fish',819,27,2,1,586,3000,20000,30000),
(6455,15,'Big Orange Fat Fish',832,28,0,1,588,3000,20000,30000),
(6456,16,'Small Purple Nimble Fish',845,29,1,1,590,3000,20000,29000),
(6457,16,'Small Purple Ugly Fish',858,29,2,1,592,3000,20000,30000),
(6458,16,'Small Purple Fat Fish',871,29,0,1,594,3000,20000,30000),
(6459,17,'Purple Nimble Fish',884,29,1,1,596,3000,20000,30000),
(6460,17,'Purple Ugly Fish',897,30,2,1,598,3000,20000,30000),
(6461,17,'Purple Fat Fish',910,29,0,1,600,3000,20000,31000),
(6462,18,'Big Purple Nimble Fish',923,31,1,1,602,3000,20000,30000),
(6463,18,'Big Purple Ugly Fish',936,31,2,1,604,3000,20000,30000),
(6464,18,'Big Purple Fat Fish',949,31,0,1,606,3000,20000,31000),
(6465,19,'Small Red Nimble Fish',962,34,1,1,608,3000,20000,28000),
(6466,19,'Small Red Ugly Fish',1125,39,2,1,610,3000,20000,29000),
(6467,19,'Small Red Fat Fish',1140,39,0,1,612,3000,20000,29000),
(6468,20,'Red Nimble Fish',1155,40,1,1,614,3000,20000,29000),
(6469,20,'Red Ugly Fish',1170,40,2,1,616,3000,20000,29000),
(6470,20,'Red Fat Fish',1185,40,0,1,618,3000,20000,30000),
(6471,21,'Big Red Nimble Fish',1200,41,1,1,620,3000,20000,29000),
(6472,21,'Big Red Ugly Fish',1215,42,2,1,622,3000,20000,29000),
(6473,21,'Big Red Fat Fish',1230,46,0,1,624,3000,20000,27000),
(6474,22,'Small White Nimble Fish',1453,56,1,1,626,2000,20000,26000),
(6475,22,'Small White Ugly Fish',1470,54,2,1,628,2000,20000,27000),
(6476,22,'Small White Fat Fish',1488,55,0,1,630,2000,20000,27000),
(6477,23,'White Nimble Fish',1505,56,1,1,632,2000,20000,27000),
(6478,23,'White Ugly Fish',1523,56,2,1,634,2000,20000,27000),
(6479,23,'White Fat Fish',1540,57,0,1,636,2000,20000,27000),
(6480,24,'Big White Nimble Fish',1558,58,1,1,638,2000,20000,27000),
(6481,24,'Big White Ugly Fish',1575,58,2,1,640,2000,20000,27000),
(6482,24,'Big White Fat Fish',1593,59,0,1,642,2000,20000,27000),
(6483,25,'Small Black Nimble Fish',1610,60,1,1,644,2000,20000,27000),
(6484,25,'Small Black Ugly Fish',1628,60,2,1,646,2000,20000,27000),
(6485,25,'Small Black Fat Fish',1648,59,0,1,648,2000,20000,28000),
(6486,26,'Black Nimble Fish',1663,67,1,1,650,2000,20000,25000),
(6487,26,'Black Ugly Fish',1872,75,2,1,652,2000,20000,25000),
(6488,26,'Black Fat Fish',1892,76,0,1,654,2000,20000,25000),
(6489,27,'Big Black Nimble Fish',1911,76,1,1,656,2000,20000,25000),
(6490,27,'Big Black Ugly Fish',1931,77,2,1,658,2000,20000,25000),
(6491,27,'Big Black Fat Fish',1950,78,0,1,660,2000,20000,25000),
(6492,1,'Small Green Treasure Chest',132,4,3,1,504,5000,20000,31000),
(6493,2,'Green Treasure Chest',188,6,3,1,510,5000,20000,31000),
(6494,3,'Big Green Treasure Chest',252,8,3,1,516,5000,20000,32000),
(6495,4,'Small Jade Treasure Chest',310,10,3,1,522,5000,20000,32000),
(6496,5,'Jade Treasure Chest',340,11,3,1,528,4000,20000,32000),
(6497,6,'Big Jade Treasure Chest',370,12,3,1,534,4000,20000,32000),
(6498,7,'Small Blue Treasure Chest',400,13,3,1,540,4000,20000,32000),
(6499,8,'Blue Treasure Chest',430,14,3,1,546,4000,20000,31000),
(6500,9,'Big Blue Treasure Chest',529,17,3,1,552,4000,20000,31000),
(6501,10,'Small Yellow Treasure Chest',564,18,3,1,558,4000,20000,31000),
(6502,11,'Yellow Treasure Chest',598,19,3,1,564,4000,20000,31000),
(6503,12,'Big Yellow Treasure Chest',633,20,3,1,570,4000,20000,31000),
(6504,13,'Small Orange Treasure Chest',667,21,3,1,576,3000,20000,32000),
(6505,14,'Orange Treasure Chest',702,22,3,1,582,3000,20000,32000),
(6506,15,'Big Orange Treasure Chest',832,28,3,1,588,3000,20000,30000),
(6507,16,'Small Purple Treasure Chest',871,29,3,1,594,3000,20000,30000),
(6508,17,'Purple Treasure Chest',910,29,3,1,600,3000,20000,31000),
(6509,18,'Big Purple Treasure Chest',949,31,3,1,606,3000,20000,31000),
(6510,19,'Small Red Treasure Chest',1140,39,3,1,612,3000,20000,29000),
(6511,20,'Red Treasure Chest',1185,40,3,1,618,3000,20000,30000),
(6512,21,'Big Red Treasure Chest',1230,46,3,1,624,3000,20000,27000),
(6513,22,'Small White Treasure Chest',1488,55,3,1,630,2000,20000,27000),
(6514,23,'White Treasure Chest',1540,57,3,1,636,2000,20000,27000),
(6515,24,'Big White Treasure Chest',1593,59,3,1,642,2000,20000,27000),
(6516,25,'Small Black Treasure Chest',1648,59,3,1,648,2000,20000,28000),
(6517,26,'Black Treasure Chest',1892,76,3,1,654,2000,20000,25000),
(6518,27,'Big Black Treasure Chest',1950,78,3,1,660,2000,20000,25000),
(7726,1,'Small Green Nimble Fish - For Beginners',100,4,5,0,400,5000,20000,35000),
(7727,1,'Small Green Ugly Fish - For Beginners',116,4,6,0,400,5000,20000,35000),
(7728,1,'Small Green Pudgy Fish - For Beginners',132,5,4,0,400,5000,20000,35000),
(7729,2,'Green Nimble Fish - For Beginners',150,5,5,0,400,5000,20000,35000),
(7730,2,'Green Ugly Fish - For Beginners',168,6,6,0,400,5000,20000,35000),
(7731,2,'Green Pudgy Fish - For Beginners',188,7,4,0,400,5000,20000,35000),
(7732,3,'Large Green Nimble Fish - For Beginners',208,7,5,0,400,5000,20000,35000),
(7733,3,'Large Green Ugly Fish - For Beginners',230,8,6,0,400,5000,20000,35000),
(7734,3,'Large Green Pudgy Fish - For Beginners',252,9,4,0,400,5000,20000,35000),
(7735,4,'Small Jade Nimble Fish - For Beginners',276,10,5,0,400,5000,20000,35000),
(7736,4,'Small Jade Ugly Fish - For Beginners',300,11,6,0,400,5000,20000,35000),
(7737,4,'Small Jade Fat Fish - For Beginners',310,11,4,0,400,5000,20000,35000),
(7738,5,'Jade Nimble Fish - For Beginners',320,11,5,0,400,5000,20000,35000),
(7739,5,'Jade Ugly Fish - For Beginners',330,12,6,0,400,4000,20000,35000),
(7740,5,'Jade Fat Fish - For Beginners',340,12,4,0,400,4000,20000,35000),
(7741,6,'Large Jade Nimble Fish - For Beginners',350,13,5,0,400,4000,20000,35000),
(7742,6,'Large Jade Ugly Fish - For Beginners',360,13,6,0,400,4000,20000,35000),
(7743,6,'Large Jade Fat Fish - For Beginners',370,13,4,0,400,4000,20000,35000),
(7744,7,'Small Blue Nimble Fish - For Beginners',380,14,5,0,400,4000,20000,35000),
(7745,7,'Small Blue Ugly Fish - For Beginners',390,14,6,0,400,4000,20000,35000),
(7746,7,'Small Blue Fat Fish - For Beginners',400,14,4,0,400,4000,20000,35000),
(7747,8,'Blue Nimble Fish - For Beginners',410,15,5,0,400,4000,20000,35000),
(7748,8,'Blue Ugly Fish - For Beginners',420,15,6,0,400,4000,20000,35000),
(7749,8,'Blue Fat Fish - For Beginners',430,15,4,0,400,4000,20000,35000),
(7750,9,'Large Blue Nimble Fish - For Beginners',506,18,5,0,400,4000,20000,35000),
(7751,9,'Large Blue Ugly Fish - For Beginners',518,19,6,0,400,4000,20000,35000),
(7752,9,'Large Blue Fat Fish - For Beginners',529,19,4,0,400,4000,20000,35000),
(7753,10,'Small Yellow Nimble Fish - For Beginners',541,19,5,0,400,4000,20000,35000),
(7754,10,'Small Yellow Ugly Fish - For Beginners',552,20,6,0,400,4000,20000,35000),
(7755,10,'Small Yellow Fat Fish - For Beginners',564,20,4,0,400,4000,20000,35000),
(7756,11,'Yellow Nimble Fish - For Beginners',575,21,5,0,400,4000,20000,35000),
(7757,11,'Yellow Ugly Fish - For Beginners',587,21,6,0,400,4000,20000,35000),
(7758,11,'Yellow Fat Fish - For Beginners',598,21,4,0,400,4000,20000,35000),
(7759,12,'Large Yellow Nimble Fish - For Beginners',610,22,5,0,400,4000,20000,35000),
(7760,12,'Large Yellow Ugly Fish - For Beginners',621,22,6,0,400,4000,20000,35000),
(7761,12,'Large Yellow Fat Fish - For Beginners',633,23,4,0,400,4000,20000,35000),
(7762,13,'Small Orange Nimble Fish - For Beginners',644,23,5,0,400,4000,20000,35000),
(7763,13,'Small Orange Ugly Fish - For Beginners',656,23,6,0,400,4000,20000,35000),
(7764,13,'Small Orange Fat Fish - For Beginners',667,24,4,0,400,3000,20000,35000),
(7765,14,'Orange Nimble Fish - For Beginners',679,24,5,0,400,3000,20000,35000),
(7766,14,'Orange Ugly Fish - For Beginners',690,25,6,0,400,3000,20000,35000),
(7767,14,'Orange Fat Fish - For Beginners',702,25,4,0,400,3000,20000,35000),
(7768,15,'Large Orange Nimble Fish - For Beginners',713,25,5,0,400,3000,20000,35000),
(7769,15,'Large Orange Ugly Fish - For Beginners',819,29,6,0,400,3000,20000,35000),
(7770,15,'Large Orange Fat Fish - For Beginners',832,30,4,0,400,3000,20000,35000),
(7771,16,'Small Purple Nimble Fish - For Beginners',845,30,5,0,400,3000,20000,35000),
(7772,16,'Small Purple Ugly Fish - For Beginners',858,31,6,0,400,3000,20000,35000),
(7773,16,'Small Purple Fat Fish - For Beginners',871,31,4,0,400,3000,20000,35000),
(7774,17,'Purple Nimble Fish - For Beginners',884,32,5,0,400,3000,20000,35000),
(7775,17,'Purple Ugly Fish - For Beginners',897,32,6,0,400,3000,20000,35000),
(7776,17,'Purple Fat Fish - For Beginners',910,33,4,0,400,3000,20000,35000),
(7777,18,'Large Purple Nimble Fish - For Beginners',923,33,5,0,400,3000,20000,35000),
(7778,18,'Large Purple Ugly Fish - For Beginners',936,33,6,0,400,3000,20000,35000),
(7779,18,'Large Purple Fat Fish - For Beginners',949,34,4,0,400,3000,20000,35000),
(7780,19,'Small Red Nimble Fish - For Beginners',962,34,5,0,400,3000,20000,35000),
(7781,19,'Small Red Ugly Fish - For Beginners',1125,40,6,0,400,3000,20000,35000),
(7782,19,'Small Red Fat Fish - For Beginners',1140,41,4,0,400,3000,20000,35000),
(7783,20,'Red Nimble Fish - For Beginners',1155,41,5,0,400,3000,20000,35000),
(7784,20,'Red Ugly Fish - For Beginners',1170,42,6,0,400,3000,20000,35000),
(7785,20,'Red Fat Fish - For Beginners',1185,42,4,0,400,3000,20000,35000),
(7786,21,'Large Red Nimble Fish - For Beginners',1200,43,5,0,400,3000,20000,35000),
(7787,21,'Large Red Ugly Fish - For Beginners',1215,43,6,0,400,3000,20000,35000),
(7788,21,'Large Red Fat Fish - For Beginners',1230,44,4,0,400,3000,20000,35000),
(7789,22,'Small White Nimble Fish - For Beginners',1453,52,5,0,400,2000,20000,35000),
(7790,22,'Small White Ugly Fish - For Beginners',1470,53,6,0,400,2000,20000,35000),
(7791,22,'Small White Fat Fish - For Beginners',1488,53,4,0,400,2000,20000,35000),
(7792,23,'White Nimble Fish - For Beginners',1505,54,5,0,400,2000,20000,35000),
(7793,23,'White Ugly Fish - For Beginners',1523,54,6,0,400,2000,20000,35000),
(7794,23,'White Fat Fish - For Beginners',1540,55,4,0,400,2000,20000,35000),
(7795,24,'Large White Nimble Fish - For Beginners',1558,56,5,0,400,2000,20000,35000),
(7796,24,'Large White Ugly Fish - For Beginners',1575,56,6,0,400,2000,20000,35000),
(7797,24,'Large White Fat Fish - For Beginners',1593,57,4,0,400,2000,20000,35000),
(7798,25,'Small Black Nimble Fish - For Beginners',1610,58,5,0,400,2000,20000,35000),
(7799,25,'Small Black Ugly Fish - For Beginners',1628,58,6,0,400,2000,20000,35000),
(7800,25,'Small Black Fat Fish - For Beginners',1645,59,4,0,400,2000,20000,35000),
(7801,26,'Black Nimble Fish - For Beginners',1663,59,5,0,400,2000,20000,35000),
(7802,26,'Black Ugly Fish - For Beginners',1872,67,6,0,400,2000,20000,35000),
(7803,26,'Black Fat Fish - For Beginners',1892,68,4,0,400,2000,20000,35000),
(7804,27,'Large Black Nimble Fish - For Beginners',1911,68,5,0,400,2000,20000,35000),
(7805,27,'Large Black Ugly Fish - For Beginners',1931,69,6,0,400,2000,20000,35000),
(7806,27,'Large Black Fat Fish - For Beginners',1950,70,4,0,400,2000,20000,35000),
(8403,1,'Small Green Nimble Fish - Upper Grade',100,4,8,2,500,5000,20000,24000),
(8404,1,'Small Green Ugly Fish - Upper Grade',116,4,9,2,502,5000,20000,27000),
(8405,1,'Small Green Pudgy Fish - Upper Grade',132,5,7,2,504,5000,20000,31000),
(8406,2,'Green Nimble Fish - Upper Grade',150,6,8,2,506,5000,20000,25000),
(8407,2,'Green Ugly Fish - Upper Grade',168,6,9,2,508,5000,20000,28000),
(8408,2,'Green Pudgy Fish - Upper Grade',188,6,7,2,510,5000,20000,31000),
(8409,3,'Large Green Nimble Fish - Upper Grade',208,8,8,2,512,5000,20000,26000),
(8410,3,'Large Green Ugly Fish - Upper Grade',230,8,9,2,514,5000,20000,29000),
(8411,3,'Large Green Pudgy Fish - Upper Grade',252,8,7,2,516,5000,20000,32000),
(8412,4,'Small Jade Nimble Fish - Upper Grade',276,10,8,2,518,5000,20000,28000),
(8413,4,'Small Jade Ugly Fish - Upper Grade',300,10,9,2,520,5000,20000,31000),
(8414,4,'Small Jade Fat Fish - Upper Grade',310,10,7,2,522,5000,20000,32000),
(8415,5,'Jade Nimble Fish - Upper Grade',320,11,8,2,524,5000,20000,30000),
(8416,5,'Jade Ugly Fish - Upper Grade',330,11,9,2,526,4000,20000,31000),
(8417,5,'Jade Fat Fish - Upper Grade',340,11,7,2,528,4000,20000,32000),
(8418,6,'Large Jade Nimble Fish - Upper Grade',350,12,8,2,530,4000,20000,30000),
(8419,6,'Large Jade Ugly Fish - Upper Grade',360,12,9,2,532,4000,20000,31000),
(8420,6,'Large Jade Fat Fish - Upper Grade',370,12,7,2,534,4000,20000,32000),
(8421,7,'Small Blue Nimble Fish - Upper Grade',380,12,8,2,536,4000,20000,31000),
(8422,7,'Small Blue Ugly Fish - Upper Grade',390,13,9,2,538,4000,20000,31000),
(8423,7,'Small Blue Fat Fish - Upper Grade',400,13,7,2,540,4000,20000,32000),
(8424,8,'Blue Nimble Fish - Upper Grade',410,13,8,2,542,4000,20000,31000),
(8425,8,'Blue Ugly Fish - Upper Grade',420,13,9,2,544,4000,20000,32000),
(8426,8,'Blue Fat Fish - Upper Grade',430,14,7,2,546,4000,20000,31000),
(8427,9,'Large Blue Nimble Fish - Upper Grade',506,17,8,2,548,4000,20000,29000),
(8428,9,'Large Blue Ugly Fish - Upper Grade',518,17,9,2,550,4000,20000,30000),
(8429,9,'Large Blue Fat Fish - Upper Grade',529,17,7,2,552,4000,20000,31000),
(8430,10,'Small Yellow Nimble Fish - Upper Grade',541,18,8,2,554,4000,20000,30000),
(8431,10,'Small Yellow Ugly Fish - Upper Grade',552,18,9,2,556,4000,20000,30000),
(8432,10,'Small Yellow Fat Fish - Upper Grade',564,18,7,2,558,4000,20000,31000),
(8433,11,'Yellow Nimble Fish - Upper Grade',575,19,8,2,560,4000,20000,30000),
(8434,11,'Yellow Ugly Fish - Upper Grade',587,19,9,2,562,4000,20000,31000),
(8435,11,'Yellow Fat Fish - Upper Grade',598,19,7,2,564,4000,20000,31000),
(8436,12,'Large Yellow Nimble Fish - Upper Grade',610,20,8,2,566,4000,20000,30000),
(8437,12,'Large Yellow Ugly Fish - Upper Grade',621,20,9,2,568,4000,20000,31000),
(8438,12,'Large Yellow Fat Fish - Upper Grade',633,20,7,2,570,4000,20000,31000),
(8439,13,'Small Orange Nimble Fish - Upper Grade',644,21,8,2,572,4000,20000,30000),
(8440,13,'Small Orange Ugly Fish - Upper Grade',656,21,9,2,574,4000,20000,31000),
(8441,13,'Small Orange Fat Fish - Upper Grade',667,21,7,2,576,3000,20000,32000),
(8442,14,'Orange Nimble Fish - Upper Grade',679,22,8,2,578,3000,20000,31000),
(8443,14,'Orange Ugly Fish - Upper Grade',690,22,9,2,580,3000,20000,31000),
(8444,14,'Orange Fat Fish - Upper Grade',702,22,7,2,582,3000,20000,32000),
(8445,15,'Large Orange Nimble Fish - Upper Grade',713,25,8,2,584,3000,20000,29000),
(8446,15,'Large Orange Ugly Fish - Upper Grade',819,27,9,2,586,3000,20000,30000),
(8447,15,'Large Orange Fat Fish - Upper Grade',832,28,7,2,588,3000,20000,30000),
(8448,16,'Small Purple Nimble Fish - Upper Grade',845,29,8,2,590,3000,20000,29000),
(8449,16,'Small Purple Ugly Fish - Upper Grade',858,29,9,2,592,3000,20000,30000),
(8450,16,'Small Purple Fat Fish - Upper Grade',871,29,7,2,594,3000,20000,30000),
(8451,17,'Purple Nimble Fish - Upper Grade',884,29,8,2,596,3000,20000,30000),
(8452,17,'Purple Ugly Fish - Upper Grade',897,30,9,2,598,3000,20000,30000),
(8453,17,'Purple Fat Fish - Upper Grade',910,29,7,2,600,3000,20000,31000),
(8454,18,'Large Purple Nimble Fish - Upper Grade',923,31,8,2,602,3000,20000,30000),
(8455,18,'Large Purple Ugly Fish - Upper Grade',936,31,9,2,604,3000,20000,30000),
(8456,18,'Large Purple Fat Fish - Upper Grade',949,31,7,2,606,3000,20000,31000),
(8457,19,'Small Red Nimble Fish - Upper Grade',962,34,8,2,608,3000,20000,28000),
(8458,19,'Small Red Ugly Fish - Upper Grade',1125,39,9,2,610,3000,20000,29000),
(8459,19,'Small Red Fat Fish - Upper Grade',1140,39,7,2,612,3000,20000,29000),
(8460,20,'Red Nimble Fish - Upper Grade',1155,40,8,2,614,3000,20000,29000),
(8461,20,'Red Ugly Fish - Upper Grade',1170,40,9,2,616,3000,20000,29000),
(8462,20,'Red Fat Fish - Upper Grade',1185,40,7,2,618,3000,20000,30000),
(8463,21,'Large Red Nimble Fish - Upper Grade',1200,41,8,2,620,3000,20000,29000),
(8464,21,'Large Red Ugly Fish - Upper Grade',1215,42,9,2,622,3000,20000,29000),
(8465,21,'Large Red Fat Fish - Upper Grade',1230,46,7,2,624,3000,20000,27000),
(8466,22,'Small White Nimble Fish - Upper Grade',1453,56,8,2,626,2000,20000,26000),
(8467,22,'Small White Ugly Fish - Upper Grade',1470,54,9,2,628,2000,20000,27000),
(8468,22,'Small White Fat Fish - Upper Grade',1488,55,7,2,630,2000,20000,27000),
(8469,23,'White Nimble Fish - Upper Grade',1505,56,8,2,632,2000,20000,27000),
(8470,23,'White Ugly Fish - Upper Grade',1523,56,9,2,634,2000,20000,27000),
(8471,23,'White Fat Fish - Upper Grade',1540,57,7,2,636,2000,20000,27000),
(8472,24,'High Grade - Large White Nimble Fish',1558,58,8,2,638,2000,20000,27000),
(8473,24,'Large White Ugly Fish - Upper Grade',1575,58,9,2,640,2000,20000,27000),
(8474,24,'Large White Fat Fish - Upper Grade',1593,59,7,2,642,2000,20000,27000),
(8475,25,'Small Black Nimble Fish - Upper Grade',1610,60,8,2,644,2000,20000,27000),
(8476,25,'Small Black Ugly Fish - Upper Grade',1628,60,9,2,646,2000,20000,27000),
(8477,25,'Small Black Fat Fish - Upper Grade',1648,59,7,2,648,2000,20000,28000),
(8478,26,'Black Nimble Fish - Upper Grade',1663,67,8,2,650,2000,20000,25000),
(8479,26,'Black Ugly Fish - Upper Grade',1872,75,9,2,652,2000,20000,25000),
(8480,26,'Black Fat Fish - Upper Grade',1892,76,7,2,654,2000,20000,25000),
(8481,27,'Large Black Nimble Fish - Upper Grade',1911,76,8,2,656,2000,20000,25000),
(8482,27,'Large Black Ugly Fish - Upper Grade',1931,77,9,2,658,2000,20000,25000),
(8483,27,'Large Black Fat Fish - Upper Grade',1950,78,7,2,660,2000,20000,25000);
