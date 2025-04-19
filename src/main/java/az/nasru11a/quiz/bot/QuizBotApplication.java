package az.nasru11a.quiz.bot;

import az.nasru11a.quiz.bot.domain.Topic;
import az.nasru11a.quiz.bot.dto.QuestionDto;
import az.nasru11a.quiz.bot.dto.TopicDto;
import az.nasru11a.quiz.bot.repository.TopicRepository;
import az.nasru11a.quiz.bot.service.impl.QuestionServiceImpl;
import az.nasru11a.quiz.bot.service.impl.TopicServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class QuizBotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuizBotApplication.class, args);
	}

	private final TopicServiceImpl topicService;
	private final QuestionServiceImpl questionService;
	private final TopicRepository topicRepository;

	void uploadTopics(String parentTopicName,
					  String topic)
	{
		if (!parentTopicName.equalsIgnoreCase("")) {
			log.info("Parent topic name is: " + parentTopicName);
			Optional<Topic> optionalTopic = topicRepository.findTopicByTopic(parentTopicName);
			Long parentTopicId = optionalTopic.get().getId();
			TopicDto topicDto = TopicDto.builder()
					.parentTopicId(parentTopicId)
					.topic(topic)
					.build();
			topicService.create(topicDto);
		} else {
			TopicDto topicDto = TopicDto.builder()
					.topic(topic)
					.build();
			topicService.create(topicDto);
		}
	}

	void uploadQuestions (String topicName,
						 String question,
						 List<String> options,
						 Integer correctOptionId,
						 String explanation)
	{
		if(question.length() > 300) {
			throw new RuntimeException("Sual mətni 300 simboldan çox ola bilməz.");
		}

		for(String option: options) {
			if (option.length()>100) {
				throw new RuntimeException("Variant mətni 100 simboldan çox ola bilməz.");
			}
		}

		Topic topic = topicRepository.findTopicByTopic(topicName)
				.orElseThrow();
		QuestionDto dto = QuestionDto.builder()
				.topic(topic)
				.question(question)
				.options(options)
				.correctOptionId(correctOptionId)
				.explanation(explanation)
				.build();

		questionService.createQuestion(dto);
	}

	@Override
	public void run(String... args) throws Exception {

//		uploadTopics("", "Məzmun və metodika");
//		uploadTopics("", "Pedaqoji və psixoloji yanaşmalar");
//		uploadTopics("", "Ümumi təkrar testləri");
//		uploadTopics("Məzmun və metodika", "Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu");
//		uploadTopics("Məzmun və metodika", "Məktəbəqədər təhsilin məqsəd və vəzifələri");
//		uploadTopics("Məzmun və metodika", "Məktəbəqədər təhsil sahəsində təhsilverənlərin, təhsilalanların, valideynlərin hüquq və vəzifələri");
//		uploadTopics("Məzmun və metodika", "Məktəbəqədər təhsilin dövlət standartları");
//		uploadTopics("Məzmun və metodika", "İnkişaf sahələri");
//		uploadTopics("Məzmun və metodika", "Ümumi təlim nəticələri");
//		uploadTopics("Məzmun və metodika", "Təlimin təşkilinə dair pedaqoji tələblər");
//		uploadTopics("Məzmun və metodika", "Səriştələr");
//		uploadTopics("Məzmun və metodika", "Fəaliyyət və onun növləri");
//		uploadTopics("Məzmun və metodika", "Oyunlar");
//		uploadTopics("Məzmun və metodika", "Gün rejimi");
//		uploadTopics("Məzmun və metodika", "Fəal təlimin xüsusiyyətləri, iş formaları, iş üsulları");
//		uploadTopics("Məzmun və metodika", "İllik və cari planlaşdırma");
//		uploadTopics("Məzmun və metodika", "Uşaq inkişafının dəyərləndirilməsi");
//		uploadTopics("Məzmun və metodika", "Təlim prosesində öyrədici, inkişafetdirici, dəstəkləyici mühitin təşkilinə verilən tələblər ( fiziki, sosial, zaman (keçici) komponentləri)");
//		uploadTopics("Məzmun və metodika", "Müəllimin peşə etikası");
//		uploadTopics("Məzmun və metodika", "Bədii təfəkkürün inkişafı məşğələsinin təşkili metodikası");
//		uploadTopics("Məzmun və metodika", "Təsviri fəaliyyət məşğələsinin təşkili metodikası");
//		uploadTopics("Təsviri fəaliyyət məşğələsinin təşkili metodikası", "Rəsm");
//		uploadTopics("Təsviri fəaliyyət məşğələsinin təşkili metodikası", "Applikasiya");
//		uploadTopics("Təsviri fəaliyyət məşğələsinin təşkili metodikası", "Yapma");
//		uploadTopics("Təsviri fəaliyyət məşğələsinin təşkili metodikası", "Quraşdırma");
//		uploadTopics("Məzmun və metodika", "Məntiqi və riyazi təfəkkürün inkişafı məşğələsinin təşkili metodikası");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "Tərbiyəçi-müəllimin peşə bacarıqları");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "Uşaqlarda yaşına görə təfəkkürün inkişaf xüsusiyyətləri: Əyani-əməli, əyani-obrazlı, sözlü - məntiqi 89");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "J.Piaje \"Zehni inkişafın mərhələləri\"");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "H.Qardner \"Çoxnövlü zəka\" nəzəriyyəsi");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "L.Viqotski \"Ali psixi funksiyanın mədəni - tarixi inkişafı\" nəzəriyyəsi");
//		uploadTopics("Pedaqoji və psixoloji yanaşmalar", "Beynəlxalq təcrübədə aktual olan məktəbəqədər təlim metodları: Montessori təlimi, Reggio Emilia təlimi");
//		uploadTopics("Ümumi təkrar testləri", "Qarışıq test 1");
//		uploadTopics("Ümumi təkrar testləri", "Qarışıq test 2");
//		uploadTopics("Ümumi təkrar testləri", "Ümumi təkrar 1");
//		uploadTopics("Ümumi təkrar testləri", "Ümumi təkrar 2");
//		uploadTopics("Ümumi təkrar testləri", "Ümumi təkrar 3");
//
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Məktəbəqədər Təhsil Qanunu Konstitusiyamızın neçənci maddəsində əks olunub?", List.of("A. 93-cü maddə", "B. 94-cü maddə", "C. 95-ci maddə", "D. 96-cı maddə"),1, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Tərbiyəçi müəllim ............... nöqtələrin yerinə uyğun gələn variantı seçin.", List.of("A. Uşaqlara qulluğu və nəzarəti həyata keçirən şəxs", "B. Fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan şəxs", "C. Nitq qüsurlu uşaqlarla işləyən şəxs", "D. Uşaqların tərbiyəsini, hərtərəfli inkişafını, məktəbəhazırlıq təlimini təşkil edən şəxs"),3, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Məktəbəqədər təhsil nədir?", List.of("A. Uşaqların, ailənin, cəmiyyətin və dövlətin təlabatlarının nəzərə alınması", "B. Məktəbəqədər yaşlı uşaqların intellektual, fiziki və mənəvi inkişafına xidmət edən ilkin bilik, bacarıq və vərdişlərin mənimsədilməsi prosesidir", "C. Məzmun komponentlərinin əlaqələndirilməsidir", "D. İnteqrativliyin təmin edilməsidir"),1, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Metodist ........... nöqtələrin yerinə uyğun gələn variantı seçin", List.of("A. Məktəbəqədər təhsil müəssisəsində təlim-tərbiyə işlərinə rəhbərliyi həyata keçirən şəxs", "B. Fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan şəxs", "C. Nitq qüsurlu uşaqlarla işləyən şəxs", "D. Uşaqların tərbiyəsini, hərtərəfli inkişafını, məktəbəhazırlıq təlimini təşkil edən şəxs"),0, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","\"Sağlamlığından, potensial imkanından, maddi təminatından asılı olmayaraq, uşaqların birgə təhsil almaq hüququnun təmin edilməsi\" fikri aiddir.", List.of("A. İnnovasiya", "B. İnteqrasiya", "C. inklüzivlik", "D. Varislik"),2, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Məktəbəqədər təhsil müəssisəsi?", List.of("A. Uşaqların, ailənin, cəmiyyətin təlabatına uyğun olan normaların məcmusudur", "B. Dövlət standartlarına uyğun olaraq məktəbəqədər yaşlı uşaqların təlimtərbiyəsini, əqli, fiziki, mənəvi inkişafını və ümumi təhsilə hazırlığını həyata keçirən təhsil müəssisəsidir ", "C. Uşaqların intelektual, fiziki və mənəvi inkişafına xidmət edən, ilkin bilik, bacarıq və vərdişlərin formalaşdırılması prosesidir", "D. Müəyyən bir mərhələdə mənimsənilməsi nəzərdə tutulan təlim nailiyyətlərinin konkret bir səviyyəsidir"),1, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Məktəbəqədər təhsil kurikulumuna aiddir", List.of("A. Təlim nəticələri, məzmun standartları, inkişaf sahələri, təlim nəticələrinin qiymətləndirilməsi", "B. Uşaq maraqlarının nəzərə alınması", "C. Təlimdə istifadə olunan iş üsulu, iş metodu, iş forması", "D. Məzmun komponentlərinin əlaqələndirilməsi"),0, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Uyğunluq pozulmuşdur", List.of("A. Differensiallaşdırma-fərdiləşmə", "B. İnnovasiya-yenilik", "C. İnklüzivlik -sağlamlıq imkanları", "D. İnteqrasiya = uşaq maraqlarının nəzərə alınması"),3, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Nitq qüsurlu uşaqlarla işləyən şəxs ............. nöqtələrin yerinə uyğun gələn variantı seçin ", List.of("A. Tərbiyəçi", "B. Loqoped", "C. Metodist", "D. Dayə"),1, "");
//		uploadQuestions("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu","Defektoloq kimdir?", List.of("A. Uşaqlara qulluğu həyata keçirən şəxs", "B. Nitq qüsurlu uşaqlarla işləyən şəxs", "C. Uşaqların inkişafını təmin edən şəxs", "D. Uşaqların əqli və fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan mütəxəssis"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Uyğunlaşdırın: \n1. Məktəbəqədər təhsilin məqsədi\n2. Məktəbəqədər təhsilin vəzifəsi\n3. Məktəbəqədər təhsilin məzmunu\na. Uşaq şəxsiyyətinin formalaşdırılması\nb. Müstəqillik va məsuliyyət hissinin formalaşdırılması\nc. Məktəbəqədər təhsilin davamlı inkişafını təmin etmək", List.of("A. 1a, 2b, 3c", "B. 1c, 2a, 3b", "C. 1b, 2c, 3a", "D. 1c, 2b, 3a"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Məktəbəqədər təhsilin məzmununa aid olan variantı seçin.", List.of("A. Təhsilin baza komponentləri əsasında sosiallaşma təlabatının yaradılması", "B. Didaktik prinsiplər", "C. Fiziki, psixi və mənəvi sağlamlığının qorunmasının təmin edilməsi", "D. İnklüziv təhsil xidmətinin göstərilməsi"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Yoldaşları ilə ünsiyyət qurmaqda çətinlik çəkən Leylada tərbiyyəçi hansı didaktik prinsipi inkişaf etdirməlidir?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Şəxsiyyǝtyönümlülük", "D. İnteqrativlik"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Uşaqlarda sadədən mürəkkəbə doğru bacarıqları inkişaf etdirən tərbiyəçi hansı didaktik prinsipi reallaşdırır?", List.of("A. Dünyəvilik", "B. Tələbyönümlülük", "C. Azərbaycançılıq", "D. Varislik"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Sada həndəsi fiqurlar haqqında məşğələ keçən tərbiyəçi müəllim, qrup işi zamanı uşaqlara plastilindan sadə fiqurlar düzəltmək tapşırığını verir. Tapşırığı yerinə yetirən uşaqlarda tərbiyəçi hansı prinsipi inkişaf etdirir?", List.of("A. İnteqrativlik", "B. Uşaqyönümlülük", "C. Şəxsiyyətyönümlülük", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Məşğələ zamanı Anarın nailiyyətini qeyd etməyən tərbiyəçi hansı didaktik prinsipi pozur?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Şəxsiyyətyönümlülük", "D. İnteqrativlik"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Məşğələ zamanı tərbiyəçi müəllim uşaqlara xalqımızın adət-ənənələri, tarixi və mədəniyyəti haqqında məlumat verir. Tərbiyəçinin inkişaf etdirdiyi didaktik prinsip?", List.of("A. Şəxsiyyǝtyönümlülük", "B. Uşaqyönümlülük", "C. Varislik", "D. Azərbaycançılıq"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Məktəbəqədər təhsilin məqsədini seçin", List.of("A. Uşaqların milli-mənəvi dəyərlər ruhunda təhsilə hazırlığını təmin etmək", "B. Cəmiyyətə sosial uyğunlaşma", "C. Müşahidə etdiklərinə münasibət bildirmək", "D. Qərar qəbul etmək və müstəqil öyrənmək "),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi yol hərəkəti qaydalarını uşaqlara öyrədir, daha sonra həyatda tətbiqini həyata keçirdir. Tərbiyyəçinin uşaqlarda formalaşdırdığı didaktik prinsip?", List.of("A. İnteqrativlik", "B. Varislik", "C. Dünyəvilik", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçinin verdiyi qrup işi məşğələnin məzmununa aid deyil. Tərbiyəçi hansı didaktik prinsipi pozub?", List.of("A. İnteqrativlik", "B. Dünyəvilik", "C. İnkişafyönümlülük", "D. Varislik"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi Vüsalın işini dəyərləndirmir. Hansı didaktik prinsip pozulub?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. İnteqrativlik", "D. Şəxsiyyətyönümlülük"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Yaş səviyyəsinin sonunda uşaqlarda uyğun davranışları formalaşdıran müəllim hansı didaktik prinsipi reallaşdırıb?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Naticǝyönümlülük", "D. Şəxsiyyǝtyönümlülük"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri"," - uşağın ehtiyaclarının müəyyən edilməsi və ona müvafiq xidmətlərin göstərilməsi\n - sosial-pedaqoji baxımdan ailənin dəyərli üzvü kimi inkişafının təmin edilməsi\n - İnklüziv təhsil xidmətlərinin göstərilməsi\nQeyd olunan xüsusiyyətlər aiddir?", List.of("A. Məktəbəqədər təhsilin məqsədi", "B. Məktəbəqədər təhsilin məzmunu", "C. Məktəbəqədər təhsilin vəzifəsi", "D. Məktəbəqədər təhsilin hüququ"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Təşkil olunan məşğələnin əsas məqsədi milli dəyərləri, adət-ənənələri uşaqlara aşılamaqdır. Tərbiyyəçi hansı didaktik prinsipi reallaşdırır?", List.of("A. Şəxsiyyətyönümlülük", "B. Uşaqyönümlülük", "C. Azərbaycançılıq", "D. Dünyəvilik"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi uşaqları qruplara bölərkən onların yaş xüsusiyyətlərini və maraqlarını nəzərə alır. Tərbiyəçi hansı didaktik prinsipə əməl edir?", List.of("A. Uşaqyönümlülük", "B. Şəxsiyyətyönümlülük", "C. İnteqrativlik", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi məşğələnin məzmununa uyğun olaraq uşaqlarda XXI əsr bacarıqlarını formalaşdırır. Tərbiyəçi hansı didaktik prinsipi reallaşdırır? ", List.of("A. Azərbaycançılıq", "B. Varislik", "C. Təlǝbyönümlülük", "D. Dünyəvilik"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyyəçi müəllim məşğələni öz istəklərinə uyğun qurur. Müəllimin pozduğu didaktik prinsip?", List.of("A.Şəxsiyyətyönümlülük", "B. Uşaqyönümlülük", "C. İnkişafyönümlülük", "D. Nəticǝyönümlülük"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi müəllim keçəcəyi məşğələnin planını əvvəlcədən hazırlamır. Hansı didaktik prinsip pozulub? ", List.of("A. İnkişafyönümlülük", "B. Nəticǝyönümlülük", "C. Varislik", "D. Tələbyönümlülük"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Orxan qrup işi zamanı jest mimikalardan istifadə edərək fikrini səlis çatdıra bilir. Tərbiyəçi Orxanda hansı didaktik prinsipi inkişaf etdirib?", List.of("A. Azərbaycançılıq", "B. Dünyəvilik", "C. İnkişafyönümlülük", "D. Şəxsiyyətyönümlülük"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi müəllim məşğələ zamanı xalqımızın tarixi, mədəniyyəti, adət-ənənələri haqqında uşaqlara məlumat verir, bunları bilməyin vacibliyini vurğulayır. Tərbiyəçinin reallaşdırdığı didaktik prinsip?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Azərbaycançılıq", "D. Varislik"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Uyğunluq pozulmuşdur:", List.of("A. Şəxsiyyətyönümlülük = idraki bacarıqlar", "B. Varislik = sadədən mürəkkəbə", "C. İnkişafyönümlülük = nailiyyətlərin dəyərləndirilməsi", "D. Dünyəvilik= tariximiz, mədəniyyətimiz"),3, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi məşğələ keçən zaman əyani vəsaitlərdən, iş formalarından və iş üsullarından istifadə etmir. Pozulan didaktik prinsip?", List.of("A. İnkişafyönümlülük", "B. Tələbyönümlülük", "C. Nəticǝyönümlülük", "D. Varislik"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Tərbiyəçi müəllim uşaqlarda sadədən mürəkkəbə doğru bacarıqları inkişaf etdirirsə hansı didaktik prinsipi reallaşdırır?", List.of("A. Nəticǝyönümlülük", "B. İnkişafyönümlülük", "C. Varislik", "D. Tələbyönümlülük"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Uyğunluq gözlənilmişdir:", List.of("A. inteqrativlik = məzmun komponentlərinin əlaqələndirilməsi", "B. İnkişafyönümlülük = uşaq maraqları", "C. Tələbyönümlülük = idraki inkişaf", "D. Varislik = dövrün tələbi"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri"," - özünün və digərlərinin hüquqlarına hörmət etmək \n - estetik dünyagörüşü, nitq, təsvir və tərtibat bacarıqları \nQeyd olunan xüsusiyyətlər aiddir?", List.of("A. Məktəbəqədər təhsilin məzmununa", "B. Məktəbəqədər təhsilin hüququna", "C. Məktəbəqədər təhsilin məqsədinə", "D. Məktəbəqədər təhsilin vəzifəsinə"),0, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Məzmun komponentlərini əlaqələndirərək məşğələ keçən tərbiyəçi uşaqları yüksək səviyyədə təlimə cəlb edir. Tərbiyəçinin reallaşdırdığı didaktik prinsip?", List.of("A. Azərbaycançılıq", "B. Dünyəvilik", "C. İnteqrativlik", "D. Varislik"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","\"Azərbaycan dünyəvi ölkədir, birmənalı şəkildə din dövlətdən ayrıdır\" fikri hansı didaktik prinsipə aiddir?", List.of("A. İnkişafyönümlülük", "B. Dünyəvilik", "C. Uşaqyönümlülük", "D. İnteqrativlik"),1, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Uyğunsuzluğu müəyyən edin", List.of("A. Məzmun komponentlərinin əlaqələndirilməsi/integrativlik ", "B. Bilik, bacarıq və vərdişlərin inkişafı / İnkişafyönümlülük", "C. Məzmun komponentlərinin tamlığı / azərbaycançılıq", "D. Uşaqların potensial imkanları / Uşaqyönümlülük"),2, "");
//		uploadQuestions("Məktəbəqədər təhsilin məqsəd və vəzifələri","Gülay şeiri əzbərləyə bilmir. Pozulan didaktik prinsip?", List.of("A. Şəxsiyyətyönümlülük", "B. İnkişafyönümlülük", "C. Nəticəyönümlülük", "D. Uşaqyönümlülük"),0, "");

		uploadTopics("", "Hər gün 20 test");
		uploadTopics("Hər gün 20 test", "Test 9");

		uploadQuestions("Test 9", "Fəal təlimin əsas xüsusiyyətlərinə aid deyil:", List.of("Uşaqlar bütün məşğələ zamanı fəal olurlar.", "Tapşırıqlar təfəkkürün inkişafına yönəlir.", "Uşaqlar fəal tədqiqatçı kimi çıxış edir.", "Əldə etdikləri yeni bilikləri yaradıcı tətbiq edə bilmirlər."),0, "");
		uploadQuestions("Test 9", "Dəyərləndirmənin bu növündə tərbiyəçi müəllim meyyar cədvəlindən istifadə edə bilər:", List.of("Diaqnostik(ilkin dəyərləndirmə).", "İnkişafın izlənməsi(formativ qiymətləndirmə).", "İnkişafa dair yekun hesabat.", "Məktəbəqədər təhsilin sonunda dəyərləndirmə."),1, "");
		uploadQuestions("Test 9", "Tərbiyəçi müəllim uşaqlar arasında baş verən munaqişənin həlli zamanı hansı alqoritmdən istifadə etməlidir?\nSəhv verilən variantı seçin:", List.of("Onlar üçün problemi yenidən ifadə etməli.", "Münaqişə tərəflərinin nə hiss etdiklərini öyrənməli.", "Tərəfdaşların qarşılıqlı maraqlarını fikirlərini və ya münaqişəyə olan baxışlarını toqquşdurmalı.", "Uşaqlardan münaqişənin həlli ilə bağlı fikirlərini söyləməlirini xahiş etməli."),2, "");
		uploadQuestions("Test 9", "Müəllim peşə nüfuzunu möhkəmləndirməyə, müəllim adını, şərəf və ləyaqətini uca tutmağa borcludur:\nHansı etik davranış qaydasına aiddir?", List.of("Qərəzsizlik", "Mədəni davranış", "İctimai etimad", "Loyallıq"),2, "");
		uploadQuestions("Test 9", "Şəkildə gördüyünüz əyani vəsaitlərdən hansı məşğələ zamanı istifadə edə bilərik?", List.of("Savad təlimi məşğələsi.", "Məntiqi və riyazi təfəkkür məşğələsi.", "Bədii təfəkkürün inkişafı məşğələsi.", "Fiziki mədəniyyət məşğələsi."),1, "");
		uploadQuestions("Test 9", "Uşaqların məktəbə hazırlığı zamanı idraki proseslərin inkişafına aid edilə bilməz:", List.of("Diqqətin inkişafı", "Hafizənin inkişafı", "Təxəyyülün inkişafı", "Emosiyaların inkişafı"),3, "");
		uploadQuestions("Test 9", "Məktəbəqədər yaşlı uşaqlarla \"söz oyunu\" oynamaq uşaqların məktəbə hazırlığı zamanı diqqət yetirməli hansı məqama aid edilə bilər?", List.of("Fiziki hazırlıq", "Nitq hazırlığı", "Emosional-iradi hazırlıq", "İdrak proseslərinin inkişafı"),1, "");
		uploadQuestions("Test 9", "Həm cari həm də perspektiv planlaşdırmaya aid olanı seçin:", List.of("Məqsəd", "Tarix", "İş forması", "Resurslar"),3, "");
//		uploadQuestions("Test 9", "Səriştənin növlərinə əsasən uyğunluğu müəyyən edin:\n1. Təfəkkürlə bağlı səriştələr\n2. Fəaliyyətlə bağlı səriştələr\n3. Dəyərlərlə baglı səriştələr\n\na. Problem həll etmə\nb. Ünsiyyət\nc. Milli kimliyin dərki, milli iftixar hissi\nd. Qərar qəbul etmə\ne. İnformasiya savadlılığı\nə. Mədəni müxtəlifliyə hörmət\nf. Fəal vətəndaş mövqeyi", List.of("1-a,c, 2-b,d, 3-e,ə,f", "1-a,d 2-b,e 3-c,ə,f", "1-b,d 2-e,ə 3-c,d,f", "1-e,ə 2-b,c 3-a,b,d,ə"),1, "");
		uploadQuestions("Test 9", "Təlimin təşkilinə verilən pedoqoji tələblərə hansılar aiddir?", List.of("Şəxsiyyətyönümlülük, tələbyünömlülük.", "Diferensiallaşma, dünyəvilik", "İdrak fəallığı, əməkdaşlıq", "Nəticəyönümlülük, inkişafyönümlülük"),2, "");
		uploadQuestions("Test 9", "Samirə özü və ailəsi haqqında ətraflı məlumat verdi. Samirədə hansı inkişaf sahəsi daha yaxşı formalaşmışdır?", List.of("Fiziki inkişaf sahəsi", "İdraki inkişaf sahəsi", "Sosial emosional inkişaf sahəsi", "Esteti yaradıcı inkişaf sahəsi"),1, "");
		uploadQuestions("Test 9", "SİM(saglamliq imkanlari mehdud)uwaqlara aiddir?", List.of("İnkluziv təhsil", "İnnovatik təhsil", "Məktəbəqədər təhsil", "Düzgün variant yoxdur"),0, "");
		uploadQuestions("Test 9", "Gün rejiminin əsas komponentlərindən hansı erkən yaşlarda uşaqlar üçün oyun səciyyəsi daşıyır?", List.of("Açıq havada qalma (gəzinti)", "Oyun fəaliyyəti", "Yuxu", "Tədris məşğələləri"),3, "");
		uploadQuestions("Test 9", "Erkən məktəbəqədər yaşlı uşaqların gün rejimlərinin əsas komponentləri:", List.of("Qidalanma - yuxu - ayıqlıq", "Oyun - yuxu - qidalanma", "Qidalanma - tədris məşğələləri - ayıqlıq", "Oyun - ayıqlıq - qidalanma"),0, "");
//		uploadQuestions("Test 9", "Verilənlərdən biri valideynlərin vəzifələrinə aiddir:", List.of("Övladı üçün məktəbəqədər təhsil müəssisəsini seçmək.", "Təhsilalanların hüquq və maraqlarını müdafiə etmək, müəssisənin ictimai idarə olunmasında iştirak etmək.", "Məktəbəqədər təhsil müəssisəsində uşaqların təhsilə marağı, qidalanması, tibbi müayinəsi və xidmət şəraiti haqqında məlumat almaq.", "Övladının erkən yaşda fiziki, əxlaqi və intellektual inkişafına şərait yaratmaq."),3, "");
		uploadQuestions("Test 9", "Müxtəlif təşəbbüs və tədqiqatlar əsasında formalaşan mütərəqqi xarakterli yeniliklər:", List.of("İnnovasiya", "Qısamüddətli təlim", "İcma əsaslı məktəbəqədər təlim", "Mektebeqeder tehsil sahesinde dovlet siyasetinin esas prinsipi"),0, "");
		uploadQuestions("Test 9", "Məşğələnin mərəhələlərinə aid olan variantı seçin:", List.of("Öyrənmə", "Oxuma", "Məqsəd", "Tətbiq etmə"),3, "");
		uploadQuestions("Test 9", "Hansı variant ümumi təlim nəticələrinə aid edilə bilər:", List.of("Günay ucadan çıxış edəcək.", "Aynurə sadə riyazi tapşırıqları yerinə yetirdi.", "Ayan anası ilə birlikdə gozəl rəsm çəkir.", "Əli bədii yaradıcılıq bacarıqlarını nümayiş etdirir."),3, "");
		uploadQuestions("Test 9", "Tək başına oyun sayılır, yalnız özləri istəsə başqasına müraciət edir:", List.of("Tək oyunlar", "Əməkdaşlıq oyunları", "Paralel oyunlar", "Sərbəst oyunlar"),3, "");
		uploadQuestions("Test 9", "Yusif balaca bacısını çox sevir, ona qarşı qayğıkeş və mehribandır və oyun zamanı onun istəklərini nəzərə alır. Yusif hansı inkişaf sahəsinə aiddir?", List.of("Sosial-emosional inkişaf", "Fiziki inkişaf, sağlamlıq və təhlükəsizlik", "Estetik və yaradıcı inkişaf", "İdraki inkişaf"),0, "");

	}
}
