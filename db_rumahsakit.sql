/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.14 : Database - db_rumahsakit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_rumahsakit` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_rumahsakit`;

/*Table structure for table `tabel_doktor` */

DROP TABLE IF EXISTS `tabel_doktor`;

CREATE TABLE `tabel_doktor` (
  `kode_doktor` varchar(6) NOT NULL DEFAULT '',
  `nama_dokter` varchar(20) DEFAULT NULL,
  `alamat_dokter` varchar(20) DEFAULT NULL,
  `keahlian` varchar(20) DEFAULT NULL,
  `no_handphone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`kode_doktor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_doktor` */

insert  into `tabel_doktor`(`kode_doktor`,`nama_dokter`,`alamat_dokter`,`keahlian`,`no_handphone`) values ('dr','kjh','jh','kjh','987'),('dr001','fghj','hj','ghj','987'),('DR01','Dr. Koko Yunaldi','Jalan Nenas','Dokter Umum','098765432321'),('dr011','tghasd','gh','gh','987'),('DR02','Dr. Arif','Jalan Bagan Besar','Dokter THT','098709870987'),('DR03','Dr. Hafis','Jalan Purnama','Dokter Gigi','012345678901');

/*Table structure for table `tabel_obat` */

DROP TABLE IF EXISTS `tabel_obat`;

CREATE TABLE `tabel_obat` (
  `Kode_obat` varchar(6) NOT NULL DEFAULT '',
  `nama_obat` varchar(10) DEFAULT NULL,
  `jenis_obat` varchar(10) DEFAULT NULL,
  `harga_obatt` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`Kode_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_obat` */

insert  into `tabel_obat`(`Kode_obat`,`nama_obat`,`jenis_obat`,`harga_obatt`) values ('1','Norths','Bubuk','1000'),('12','Fruentazia','Cairan','100000'),('123','Paracetamo','Cairan','20000'),('1234','Detroit','Capsule','10000'),('12345','Dark','Capsule','20000'),('123456','Fruit','Capsule','200000');

/*Table structure for table `tabel_pasien` */

DROP TABLE IF EXISTS `tabel_pasien`;

CREATE TABLE `tabel_pasien` (
  `kode_pasien` varchar(6) NOT NULL DEFAULT '',
  `nama_pasien` varchar(25) DEFAULT NULL,
  `alamat_pasien` varchar(25) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `jenis_kelamin` varchar(10) DEFAULT NULL,
  `kepala_keluarga` varchar(25) DEFAULT NULL,
  `handphone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`kode_pasien`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_pasien` */

insert  into `tabel_pasien`(`kode_pasien`,`nama_pasien`,`alamat_pasien`,`tanggal_lahir`,`jenis_kelamin`,`kepala_keluarga`,`handphone`) values ('A001','Andrea Hinata','Jalan Nenas2','1992-05-27',' PEREMPUAN','Muhammad Santoso','098765678900');

/*Table structure for table `tabel_periksa` */

DROP TABLE IF EXISTS `tabel_periksa`;

CREATE TABLE `tabel_periksa` (
  `No_Periksa` varchar(5) NOT NULL DEFAULT '',
  `tgl_periksa` date DEFAULT NULL,
  `kode_doktor` varchar(6) DEFAULT NULL,
  `kode_pasien` varchar(6) DEFAULT NULL,
  `gejala` varchar(30) DEFAULT NULL,
  `penyakit` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`No_Periksa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_periksa` */

insert  into `tabel_periksa`(`No_Periksa`,`tgl_periksa`,`kode_doktor`,`kode_pasien`,`gejala`,`penyakit`) values ('01','2016-12-03','DR02','A001','Gejala1','tipus');

/*Table structure for table `tabel_rawat` */

DROP TABLE IF EXISTS `tabel_rawat`;

CREATE TABLE `tabel_rawat` (
  `kode_rawat` varchar(6) NOT NULL DEFAULT '',
  `No_Periksa` varchar(6) DEFAULT NULL,
  `kode_pasien` varchar(255) DEFAULT NULL,
  `kode_doktor` varchar(6) DEFAULT NULL,
  `Kode_obat` varchar(6) DEFAULT NULL,
  `tanggal_masuk` date DEFAULT NULL,
  `tanggal_keluar` date DEFAULT NULL,
  `lama_inap` int(3) DEFAULT NULL,
  `biaya_perhari` bigint(8) DEFAULT NULL,
  `total_hargaobat` bigint(8) DEFAULT NULL,
  `total_biaya` bigint(8) DEFAULT NULL,
  PRIMARY KEY (`kode_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabel_rawat` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
