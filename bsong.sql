-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2021 at 09:46 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bsong`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Nhạc Hải Ngoại'),
(2, 'Nhạc Trung'),
(3, 'Nhạc Thiếu Nhi'),
(4, 'Nhạc Pop'),
(8, 'Nhạc Rock'),
(9, 'Nhạc Đỏ'),
(10, 'Nhạc V-POP');

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `email`, `website`, `message`) VALUES
(1, 'Lâm Ngọc Khương', 'chatwithme9x@gmail.com', 'https://ngockhuong.com', 'Liên hệ admin trang Liên hệ admin trang Liên hệ admin trangLiên hệ admin trangLiên hệ admin trangLiên hệ admin trangLiên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang Liên hệ admin trang'),
(2, 'Lâm Ngọc Khương', 'chatwithme9x@gmail.com', 'https://ngockhuong.com', 'Liên hệ admin trang'),
(3, 'Trần Văn Sơn', 'sontv@vinaenter.com', 'http://vinaenter.edu.vn', 'Liên hệ admin trang'),
(4, 'Hoa Hồng', 'chatwithme9x@gmail.com', 'http://vinaenter.edu.vn', 'Liên hệ admin trang');

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `preview_text` text COLLATE utf8_unicode_ci NOT NULL,
  `detail_text` text COLLATE utf8_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `picture` text COLLATE utf8_unicode_ci NOT NULL,
  `counter` int(11) NOT NULL DEFAULT 0,
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`id`, `name`, `preview_text`, `detail_text`, `date_create`, `picture`, `counter`, `cat_id`) VALUES
(5, 'dfgdfgfdgfdg', 'fdgfdgfgfdg', 'dfgdfdg', '2017-06-27 09:07:06', '', 1, 5),
(6, 'asdsadas', 'ádasdasd', '<p><img alt=\"\" src=\"/bsong/userfiles/images/hinhnen.jpg\" style=\"height:499px; width:800px\" /></p>\r\n', '2017-06-27 09:06:54', '', 8, 7),
(9, 'ádsad', 'sdsadsa', '', '2017-06-27 09:07:15', '', 1, 7),
(14, 'Lạc Trôi', '\r\nCa khúc Lạc Trôi do ca sĩ Sơn Tùng M-TP thể hiện, thuộc thể loại nhạc Pop. Các bạn có thể nghe, download (tải nhạc) bài hát lac troi (masew remix) mp3, playlist/album, MV/Video lac troi (masew remix) miễn phí tại <a href=\"https://www.youtube.com/watch?v=Llw9Q6akRo4\">Thêm</a>', '<h3>Lời bài hát </h3>\r\nNgười theo hương hoa mây mù giăng lối</br>\r\nLàn sương khói phôi phai đưa bước ai xa rồi</br>\r\nĐơn côi mình ta vấn vương</br>\r\nHồi ức, trong men say chiều mưa buồn</br>\r\nNgăn giọt lệ ngừng khiến khoé mi sầu bi</br>\r\nĐường xưa nơi cố nhân từ giã biệt li</br>\r\n(Cánh hoa rụng rơi)</br>\r\nPhận duyên mong manh rẽ lối trong mơ ngày tương phùng</br>\r\nOh-h-h-h</br>\r\nTiếng khóc cuốn theo làn gió bay</br>\r\nThuyền ai qua sông lỡ quên vớt ánh trăng tàn nơi này</br>\r\nTrống vắng bóng ai dần hao gầy</br>\r\nLòng ta xin nguyện khắc ghi trong tim tình nồng mê say</br>\r\nMặc cho tóc mây vương lên đôi môi cay</br>\r\nBâng khuâng mình ta lạc trôi giữa đời</br>\r\nTa lạc trôi giữa trời</br>\r\nĐôi chân lang thang về nơi đâu?</br>\r\nBao yêu thương giờ nơi đâu?</br>\r\nCâu thơ tình xưa vội phai mờ</br>\r\nTheo làn sương tan biến trong cõi mơ</br>\r\nMưa bụi vương trên làn mi mắt</br>\r\nNgày chia lìa hoa rơi buồn hiu hắt</br>\r\nTiếng đàn ai thêm sầu tương tư lặng mình trong chiều hoàng hôn</br>\r\nTan vào lời ca</br>\r\nLối mòn đường vắng một mình ta</br>\r\nNắng chiều vàng úa nhuộm ngày qua</br>\r\nXin đừng quay lưng xoá</br>\r\nĐừng mang câu hẹn ước kia rời xa</br>\r\nYên bình nơi nào đây</br>\r\nChôn vùi theo làn mây</br>\r\nEh-h-h-h-h, la-la-la-la-a-a</br>\r\nNgười theo hương hoa mây mù giăng lối</br>\r\nLàn sương khói phôi phai đưa bước ai xa rồi</br>\r\nĐơn côi mình ta vấn vương hồi ức trong men say chiều mưa buồn</br>\r\nNgăn giọt lệ ngừng khiến khoé mi sầu bi</br>\r\nĐường xưa nơi cố nhân từ giã biệt li</br>\r\n(Cánh hoa rụng rơi)</br>\r\nPhận duyên mong manh rẽ lối trong mơ ngày tương phùng</br>\r\nOh-h-h-h</br>\r\nTiếng khóc cuốn theo làn gió bay</br>\r\nThuyền ai qua sông lỡ quên vớt ánh trăng tàn nơi này</br>\r\nTrống vắng bóng ai dần hao gầy</br>\r\nLòng ta xin nguyện khắc ghi trong tim tình nồng mê say</br>\r\nMặc cho tóc mây vương lên đôi môi cay</br>\r\nBâng khuâng mình ta lạc trôi giữa đời</br>\r\nTa lạc trôi giữa trời</br>\r\nTa lạc trôi (lạc trôi)</br>\r\nTa lạc trôi giữa đời</br>\r\nLạc trôi giữa trời</br>\r\nYeah, ah-h-h-h-h-h', '2020-05-08 10:34:11', 'lac-troi-son-tung-mtp-145769262458600.png', 1, 4),
(15, 'Lil Nas X - Old Town Road (Official Movie) ft. Billy Ray Cyrus', 'Ca khúc Old Town Road do ca sĩ Montero Lamar Hill, William Ray Cyrus thể hiện, thuộc thể loại Country. Các bạn có thể nghe, download (tải nhạc) bài hát old town road (remix) mp3, playlist/album, MV/Video old town road (remix) miễn phí<a href=\"https://www.youtube.com/watch?v=w2Ov5jzm3j8\"> tại  đây</a>.', 'Yeah, I\'m gonna take my horse to the old town road</br>\r\nI\'m gonna ride \'til I can\'t no more</br>\r\nI\'m gonna take my horse to the old town road</br>\r\nI\'m gonna ride \'til I can\'t no more (Kio, Kio)</br>\r\nI got the horses in the back</br>\r\nHorse tack is attached</br>\r\nHat is matte black</br>\r\nGot the boots that\'s black to match</br>\r\nRidin\' on a horse, ha</br>\r\nYou can whip your Porsche</br>\r\nI been in the valley</br>\r\nYou ain\'t been up off that porch, now</br>\r\nCan\'t nobody tell me nothin\'</br>\r\nYou can\'t tell me nothin\'</br>\r\nCan\'t nobody tell me nothin\'</br>\r\nYou can\'t tell me nothin\'</br>\r\nRidin\' on a tractor</br>\r\nLean all in my bladder</br>\r\nCheated on my baby</br>\r\nYou can go and ask her</br>\r\nMy life is a movie</br>\r\nBull ridin\' and boobies</br>\r\nCowboy hat from Gucci</br>\r\nWrangler on my booty</br>\r\nCan\'t nobody tell me nothin\'</br>\r\nYou can\'t tell me nothin\'</br>\r\nCan\'t nobody tell me nothin\'</br>\r\nYou can\'t tell me nothin\'</br>\r\nYeah, I\'m gonna take my horse to the old town road</br>\r\nI\'m gonna ride \'til I can\'t no more</br>\r\nI\'m gonna take my horse to the old town road</br>\r\nI\'m gonna ride \'til I can\'t no more</br>\r\nI got the</br>', '2020-05-08 10:40:11', 'old-town-road-video-gq-2019-051719-15650819736791834340278-146156599731700.jpg', 0, 4),
(16, 'Lá Thư Trần Thế', 'Ca khúc Lá thư trần thế do ca sĩ Duy Khánh, Hương Lan thể hiện, sáng tác: Hoài Linh, thuộc thể loại Nhạc Noel. Ca khúc nằm trong Album Bài Thánh Ca Buồn - Merry Christmas ', 'Bài hát Lá thư trần thế - Duy Khánh, Hương Lan</br>\r\n\r\nSáng tác: Hoài Linh</br>\r\n\r\nLạy Chúa con là lính trận ngoài biên</br>\r\n\r\nVì xa thành phố xa quá nên quên.</br>\r\nĐêm nay ngôi hai trời xuống</br>\r\nÁnh sao lung linh muôn màu,</br>\r\nCon tưởng hỏa châu soi tuyến đầu</br>\r\n\r\nLạy Chúa con là thiếu phụ miền quê</br>\r\nChồng con vì nước nên đã ra đi</br>\r\nHai ba năm chưa thỏa chí</br>\r\nHết Thu qua Xuân sang Hè</br>\r\nCon đợi tàn Đông mới tin về.</br>\r\n\r\nĐạn xé không trung đêm đừng đêm vẫn nghe</br>\r\nTừng lớp trai đi cho ngày mai vẫn đi</br>\r\nĐêm nay Người xuống đời</br>\r\nXin đem nguồn vui tới</br>\r\nNhững đôi môi cằn cỗi lâu không cười.</br>\r\n\r\nLạy Chúa con còn lứa tuổi học sinh</br>\r\nVì cha là lính con thiết tha xin</br>\r\nAn vui cho người đầu tuyến</br>\r\nTrẻ thơ yên tâm sách đèn</br>\r\nĐể mẹ hiền con hết ưu phiền.</br>', '2020-05-08 11:12:09', 'maxresdefault-148074811212200.jpg', 0, 1),
(17, 'Hãy Trao Cho Anh - Sơn Tùng', 'Ca khúc Hãy Trao Cho Anh do ca sĩ Sơn Tùng M-TP, Snoop Dogg thể hiện, thuộc thể loại Nhạc Trẻ. Các bạn có thể nghe, download (tải nhạc) bài hát hay trao cho anh mp3, playlist/album, MV/Video hay trao cho anh miễn phí <a href=\"https://www.youtube.com/watch?v=knW7-x7Y7RE\">tại đây</a>. ', '<h3>HÃY TRAO CHO ANH </h3>\r\nSơn Tùng M-TP </br>\r\n\r\nBóng ai đó nhẹ nhàng vụt qua nơi đây </br>\r\nQuyến rũ ngây ngất loạn nhịp làm tim mê say </br>\r\nCuốn lấy áng mây theo cơn sóng xô dập dìu </br>\r\nNụ cười ngọt ngào cho ta tan vào phút giây miên man quên hết con đường về eh </br>\r\nChẳng thể tìm thấy lối về ehhhhh </br>\r\nĐiệu nhạc hòa quyện trong ánh mắt đôi môi </br>\r\nDẫn lối những bối rối rung động khẽ lên ngôi </br>\r\n\r\nChạm nhau mang vô vàn </br>\r\nĐắm đuối vấn vương dâng tràn </br>\r\nLấp kín chốn nhân gian </br>\r\nLàn gió hóa sắc hương mơ màng </br>\r\nMột giây ngang qua đời </br>\r\nCất tiếng nói không nên lời </br>\r\nẤm áp đến trao tay ngàn sao trời lòng càng thêm chơi vơi </br>\r\nDịu êm không gian bừng sáng đánh thức muôn hoa mừng </br>\r\nQuấn quít hát ngân nga từng chút níu bước chân em dừng </br>\r\nBao ý thơ tương tư ngẩn ngơ </br>\r\nLưu dấu nơi mê cung đẹp thẫn thờ </br>\r\n\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh thứ anh đang mong chờ </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy mau làm điều ta muốn vào khoảnh khắc này đê </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy trao anh trao cho anh đi những yêu thương nồng cháy </br>\r\nTrao anh ái ân nguyên vẹn đong đầy </br>\r\n\r\nLooking at my Gucci is about that time </br>\r\nWe can smoke a blunt and pop a bottle of wine </br>\r\nNow get yourself together and be ready by nine </br>\r\nCuz we gon’ do some things that will shatter your spine </br>\r\nCome one, undone, Snoop Dogg, Son Tung </br>\r\nLong Beach is the city that I come from </br>\r\nSo if you want some, get some </br>\r\nBetter enough take some, take some </br>\r\n\r\n\r\nChạm nhau mang vô vàn </br>\r\nĐắm đuối vấn vương dâng tràn </br>\r\nLấp kín chốn nhân gian làn </br>\r\nGió hóa sắc hương mơ màng </br>\r\nMột giây ngang qua đời </br>\r\nCất tiếng nói không nên lời </br>\r\nẤm áp đến trao tay ngàn sao trời lòng càng thêm chơi vơi </br>\r\nDịu êm không gian bừng sáng đánh thức muôn hoa mừng </br>\r\nQuấn quít hát ngân nga từng chút níu bước chân em dừng </br>\r\nBao ý thơ tương tư ngẩn ngơ </br>\r\nLưu dấu nơi mê cung đẹp thẫn thờ </br>\r\n\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh thứ anh đang mong chờ </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy mau làm điều ta muốn vào khoảnh khắc này đê </br>\r\nHãy trao cho anh </br>\r\nHãy trao cho anh </br>\r\nHãy trao anh trao cho anh đi những yêu thương nồng cháy </br>\r\nTrao anh ái ân nguyên vẹn đong đầy </br>', '2020-05-10 12:01:59', 'maxresdefault-502341593500.jpg', 1, 10),
(18, 'Tô Mặc Già / 苏幕遮 - Trương Hiểu Đường (Tansea)', 'Ca khúc Tô Mặc Già / 苏幕遮 do ca sĩ Trương Hiểu Đường (Tansea) thể hiện, thuộc thể loại Nhạc Hoa. Các bạn có thể nghe, download (tải nhạc) bài hát to mac gia / 苏幕遮 mp3, playlist/album, MV/Video to mac gia / 苏幕遮 miễn phí <a href = \"https://www.youtube.com/watch?v=h0e4OWiuMlI\">tại đây</a> ', 'Báo hàn qīng yī diǎnzhuì zhè pōmò huà shānshuǐ </br>\r\nYōng zhěng xiān qiàn shǒu jiào luòyángzhǐguì </br>\r\nKělián luòhuā kòu yù zhěn fúxiù rén hái hūnshuì </br>\r\nQīngfēng wēi hán rě yī xiāng fěndài yòu chóuméi </br>\r\n\r\nDuì jìng shūzhuāng tàntīng rén shēng dǐngfèi </br>\r\nDuōqíng zì shì duō zhān rě </br>\r\nMèng duàn bùchéng guī jǐ fēn qiáocuì </br>\r\nJǐn sè wúduān shēng huǐ yíngdé mǎn xíng lèi </br>\r\n\r\nZhídào jūn xīn bù měi rìyè dōng liúshuǐ </br>\r\nSī yōuyōu hèn yōuyōu hé shí fāngshǐ xiū bàn jiāng xìn bàn jiāng chóu </br>\r\nGōng (gong) chóu huǎnghū jiāo bēi quàn liú jǐ xiǎobèi </br>\r\nQí féng hóngyán yī zuì qiān jūn wàn mǎ tuì </br>\r\n\r\nJiǎn bùduàn lǐ hái luàn gěngyàn suǒ qīng hóu yǐn qū gāncháng suì </br>\r\nQīngfēng wēi hán rě yī xiāng fěndài yòu chóuméi </br>\r\nDuì jìng shūzhuāng tàntīng rén shēng dǐngfèi </br>\r\nDuōqíng zì shì duō zhān rě nóng xiāng chuī jǐn wèi shéi </br>\r\n\r\nMèng duàn bùchéng guī jǐ fēn qiáocuì </br>\r\nBùfèn bùfèn: qiūsè lián bō bō shàng hán yān cuì shān yìng xiéyáng tiān jiē shuǐ </br>\r\nFāng cǎo wúqíng gèng zài xiéyáng wài </br>\r\nYè yè hǎo mèng liú rén shuì lóu gāo xiū dú yī </br>', '2020-06-09 15:51:31', 'artworks-000384114417-thjg4r-t500x500-1379542393700.jpg', 2, 2),
(19, 'Nắng Ấm Xa Dần', 'Nắng Ấm Xa Dần -Sơn Tùng (M-TP) do ca sĩ Đang Cập Nhật thể hiện, thuộc thể loại . Các bạn có thể nghe, download (tải nhạc) các bài hát trong playlist/album nang am xa dan -son tùng (m-tp) mp3 miễn phí tại <a href=\"https://youtu.be/ErhGuwNgrmw\">Tại Đây</a>', 'Nắng ấm xa dần rồi. <br />\r\nNắng ấm xa dần rồi.<br />\r\nNắng ấm xa dần bỏ rơi, để lại những giấc mơ .<br />\r\n(Giữ lại đi, giữ lại đi.)<br />\r\nNắng ấm xa dần rồi.<br />\r\nNắng ấm xa dần rồi.<br />\r\nNắng ấm xa dần, xa dần theo những tiếng cười .<br />\r\n(Hãy mang đi giúp những nỗi buồn)<br />\r\nTheo thời gian những hạt mưa như nặng thêm.<br />\r\nXóa hết thương yêu mặn nồng ngày nào giữa chúng ta.\r\nAnh lục tìm vẫn cứ mãi lục tìm.<br />\r\nGiơ bàn tay cố kìm nén những cảm xúc.<br />\r\nVùi mình vào đêm đen anh chẳng tìm thấy lối ra.<br />\r\nSau lưng là tiếng nói yêu anh, chẳng rời xa anh.<br />\r\nTrước mắt anh điều đấy, nó dối trá, tại sao người vội quên mau?<br />\r\nLà vì em.<br />\r\nBài ca anh viết sẽ không được trọn vẹn đâu em.<br />\r\nBước đi.<br />\r\nEm yêu một ai thật rồi mãi chẳng là anh đâu.<br />\r\nVậy thì người cứ bước đi xa nơi này.<br />\r\nÁnh bình minh sẽ không còn nơi đây.<br />\r\nBước đi xa nơi này.<br />\r\nSẽ không còn nơi đây.<br />\r\nPhải tự đứng lên mà thôi, che nhẹ đi những niềm đau và nỗi buồn.<br />\r\nXung quanh anh giờ đây cô đơn mình anh ôm giấc mơ.<br />\r\nNhìn em bước ra đi xa dần.<br />\r\nEhhhhhh... .<br />\r\nEm bước ra đi xa dần.<br />\r\nEhhhhh...<br />\r\nNhìn em bước ra đi xa dần.<br />\r\nEhhhhh...<br />\r\nNhìn em bước ra đi xa dần...<br />\r\nĐến rồi lại đi.<br />\r\nCứ vội vàng đi.<br />\r\nTrao cho anh bao yêu thương rồi em lại bỏ đi.<br />\r\nGieo trong anh bao nhiêu niềm đau, rồi em mau,<br />\r\nRời bỏ anh, xa anh, quay mặt lặng lẽ quên mau.<br />\r\nUhhh.<br />\r\nEm yêu quên thật rồi.<br />\r\nUhhh.<br />\r\nChẳng một lời chia li, quên rồi, em yêu quên rồi, quên rồi ...<br />\r\nVậy thì người cứ bước đi xa nơi này.<br />\r\nÁnh bình minh sẽ không còn nơi đây.<br />\r\nBước đi xa nơi này.<br />\r\nSẽ không còn nơi đây.<br />\r\nPhải tự đứng lên mà thôi, che nhẹ đi những niềm đau và nỗi buồn.<br />\r\nXung quanh anh giờ đây cô đơn mình anh ôm giấc mơ.<br />\r\nVậy thì người cứ bước đi xa nơi này.<br />\r\nÁnh bình minh sẽ không còn nơi đây.<br />\r\nBước đi xa nơi này.<br />\r\nSẽ không còn nơi đây.<br />\r\nPhải tự đứng lên mà thôi, che nhẹ đi những niềm đau và nỗi buồn.<br />\r\nXung quanh anh giờ đây cô đơn mình anh ôm giấc mơ.<br />\r\nNhìn em bước ra đi xa dần.<br />\r\nEhhhhhh.<br />\r\nNhìn em bước ra đi xa dần.<br />\r\nEhhh... Year...<br />\r\nNhìn em, nhìn em bước đi.<br />\r\nEhhh... Year...<br />\r\nNhìn em bước ra đi xa dần.<br />\r\nEhhh... Year...<br />\r\nNắng ấm xa dần rồi .<br />\r\nNắng ấm xa dần rồi.<br />\r\nNắng ấm xa dần bỏ rơi, để lại những giấc mơ.(giữ lại đi, giữ lại đi.)<br />\r\nNắng ấm xa dần rồi.<br />\r\nNắng ấm xa dần, xa dần theo những tiếng cười.<br />\r\n(Hãy mang đi giúp những nỗi buồn)<br />', '2020-06-09 15:57:35', 'maxresdefault-6660155548000.jpg', 1, 10),
(24, 'Hải Đẹp Trai', '', '', '2020-06-09 16:13:31', '1-533538195205000.jpg', 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'Administrator'),
(2, 'admindemo', 'e10adc3949ba59abbe56e057f20f883e', 'Admin Demo'),
(8, 'ngockhuong', '202cb962ac59075b964b07152d234b70', 'Lâm Ngọc Khương'),
(9, 'tranhai', '202cb962ac59075b964b07152d234b70', 'Trần Xuân Hải'),
(10, 'manhme', 'e10adc3949ba59abbe56e057f20f883e', 'Đinh Công Mạnh'),
(11, 'dấdasd', '123', 'dsada');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
