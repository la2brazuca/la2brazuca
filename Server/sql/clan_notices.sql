SET FOREIGN_KEY_CHECKS=0;
--
-- Table structure for 'clan_notices'
--
CREATE TABLE IF NOT EXISTS `clan_notices` (
  `clan_id` int(32) NOT NULL,
  `notice` varchar(512) NOT NULL,
  `enabled` varchar(5) NOT NULL,
  PRIMARY KEY  (`clan_id`)
);
