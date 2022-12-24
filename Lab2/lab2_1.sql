CREATE TABLE `human_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surnname` varchar(50) NOT NULL,
  `jobtitle` varchar(50) NOT NULL,
  `dateofbirth` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO my_books (id, `name`, `surnname`, `jobtitle`, `dateofbirth`) VALUES (id, 'Denis', 'Plaksin', 'Worker', '2000');
