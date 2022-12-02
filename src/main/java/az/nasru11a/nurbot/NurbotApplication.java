package az.nasru11a.nurbot;

import az.nasru11a.nurbot.domain.Topic;
import az.nasru11a.nurbot.dto.QuestionDto;
import az.nasru11a.nurbot.dto.TopicDto;
import az.nasru11a.nurbot.repository.TopicRepository;
import az.nasru11a.nurbot.service.impl.QuestionServiceImpl;
import az.nasru11a.nurbot.service.impl.TopicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class NurbotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NurbotApplication.class, args);
	}

	private final TopicServiceImpl topicService;
	private final QuestionServiceImpl questionService;
	private final TopicRepository topicRepository;

	void uploadQuestions (Long topicId,
						 String question,
						 List<String> options,
						 Integer correctOptionId,
						 String explanation)
	{
		Topic topic = topicRepository.findById(topicId).orElseThrow();
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

		topicService.create(TopicDto.builder().topic("Məzmun və metodika").build());
		topicService.create(TopicDto.builder().topic("Pedaqoji və psixoloji yanaşmalar").build());
		topicService.create(TopicDto.builder().topic("Ümumi təkrar testləri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Məktəbəqədər təhsil haqqında Azərbaycan Respublikasının qanunu").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Məktəbəqədər təhsilin məqsəd və vəzifələri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Məktəbəqədər təhsil sahəsində təhsilverənlərin, təhsilalanların, valideynlərin hüquq və vəzifələri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Məktəbəqədər təhsilin dövlət standartları").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("İnkişaf sahələri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Ümumi təlim nəticələri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Təlimin təşkilinə dair pedaqoji tələblər").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Səriştələr").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Fəaliyyət və onun növləri").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Oyunlar").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Gün rejimi").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Fəal təlimin xüsusiyyətləri, iş formaları, iş üsulları").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("İllik və cari planlaşdırma").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Uşaq inkişafının dəyərləndirilməsi").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Təlim prosesində öyrədici, inkişafetdirici, dəstəkləyici mühitin təşkilinə verilən tələblər ( fiziki, sosial, zaman (keçici) komponentləri)").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Müəllimin peşə etikası").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Bədii təfəkkürün inkişafı məşğələsinin təşkili metodikası").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Təsviri fəaliyyət məşğələsinin təşkili metodikası").build());
		topicService.create(TopicDto.builder().parentTopicId(21L).topic("Rəsm").build());
		topicService.create(TopicDto.builder().parentTopicId(21L).topic("Applikasiya").build());
		topicService.create(TopicDto.builder().parentTopicId(21L).topic("Yapma").build());
		topicService.create(TopicDto.builder().parentTopicId(21L).topic("Quraşdırma").build());
		topicService.create(TopicDto.builder().parentTopicId(1L).topic("Məntiqi və riyazi təfəkkürün inkişafı məşğələsinin təşkili metodikası").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("Tərbiyəçi-müəllimin peşə bacarıqları").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("Uşaqlarda yaşına görə təfəkkürün inkişaf xüsusiyyətləri: Əyani-əməli, əyani-obrazlı, sözlü - məntiqi 89").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("J.Piaje \"Zehni inkişafın mərhələləri\"").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("H.Qardner \"Çoxnövlü zəka\" nəzəriyyəsi").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("L.Viqotski \"Ali psixi funksiyanın mədəni - tarixi inkişafı\" nəzəriyyəsi").build());
		topicService.create(TopicDto.builder().parentTopicId(2L).topic("Beynəlxalq təcrübədə aktual olan məktəbəqədər təlim metodları: Montessori təlimi, Reggio Emilia təlimi").build());
		topicService.create(TopicDto.builder().parentTopicId(3L).topic("Qarışıq test - 1").build());
		topicService.create(TopicDto.builder().parentTopicId(3L).topic("Qarışıq test - 2").build());
		topicService.create(TopicDto.builder().parentTopicId(3L).topic("Ümumi təkrar 1").build());
		topicService.create(TopicDto.builder().parentTopicId(3L).topic("Ümumi təkrar 2").build());
		topicService.create(TopicDto.builder().parentTopicId(3L).topic("Ümumi təkrar 3").build());

//		uploadQuestions(4L,"Məktəbəqədər Təhsil Qanunu Konstitusiyamızın neçənci maddəsində əks olunub?", List.of("A. 93-cü maddə", "B. 94-cü maddə", "C. 95-ci maddə", "D. 96-cı maddə"),1, "");
//		uploadQuestions(4L,"Tərbiyəçi müəllim ............... nöqtələrin yerinə uyğun gələn variantı seçin.", List.of("A. Uşaqlara qulluğu və nəzarəti həyata keçirən şəxs", "B. Fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan şəxs", "C. Nitq qüsurlu uşaqlarla işləyən şəxs", "D. Uşaqların tərbiyəsini, hərtərəfli inkişafını, məktəbəhazırlıq təlimini təşkil edən şəxs"),3, "");
//		uploadQuestions(4L,"Məktəbəqədər təhsil nədir?", List.of("A. Uşaqların, ailənin, cəmiyyətin və dövlətin təlabatlarının nəzərə alınması", "B. Məktəbəqədər yaşlı uşaqların intellektual, fiziki və mənəvi inkişafına xidmət edən ilkin bilik, bacarıq və vərdişlərin mənimsədilməsi prosesidir", "C. Məzmun komponentlərinin əlaqələndirilməsidir", "D. İnteqrativliyin təmin edilməsidir"),1, "");
//		uploadQuestions(4L,"Metodist ........... nöqtələrin yerinə uyğun gələn variantı seçin", List.of("A. Məktəbəqədər təhsil müəssisəsində təlim-tərbiyə işlərinə rəhbərliyi həyata keçirən şəxs", "B. Fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan şəxs", "C. Nitq qüsurlu uşaqlarla işləyən şəxs", "D. Uşaqların tərbiyəsini, hərtərəfli inkişafını, məktəbəhazırlıq təlimini təşkil edən şəxs"),0, "");
//		uploadQuestions(4L,"\"Sağlamlığından, potensial imkanından, maddi təminatından asılı olmayaraq, uşaqların birgə təhsil almaq hüququnun təmin edilməsi\" fikri aiddir.", List.of("A. İnnovasiya", "B. İnteqrasiya", "C. inklüzivlik", "D. Varislik"),2, "");
//		uploadQuestions(4L,"Məktəbəqədər təhsil müəssisəsi?", List.of("A. Uşaqların, ailənin, cəmiyyətin təlabatına uyğun olan normaların məcmusudur", "B. Dövlət standartlarına uyğun olaraq məktəbəqədər yaşlı uşaqların təlimtərbiyəsini, əqli, fiziki, mənəvi inkişafını və ümumi təhsilə hazırlığını həyata keçirən təhsil müəssisəsidir ", "C. Uşaqların intelektual, fiziki və mənəvi inkişafına xidmət edən, ilkin bilik, bacarıq və vərdişlərin formalaşdırılması prosesidir", "D. Müəyyən bir mərhələdə mənimsənilməsi nəzərdə tutulan təlim nailiyyətlərinin konkret bir səviyyəsidir"),1, "");
//		uploadQuestions(4L,"Məktəbəqədər təhsil kurikulumuna aiddir", List.of("A. Təlim nəticələri, məzmun standartları, inkişaf sahələri, təlim nəticələrinin qiymətləndirilməsi", "B. Uşaq maraqlarının nəzərə alınması", "C. Təlimdə istifadə olunan iş üsulu, iş metodu, iş forması", "D. Məzmun komponentlərinin əlaqələndirilməsi"),0, "");
//		uploadQuestions(4L,"Uyğunluq pozulmuşdur", List.of("A. Differensiallaşdırma-fərdiləşmə", "B. İnnovasiya-yenilik", "C. İnklüzivlik -sağlamlıq imkanları", "D. İnteqrasiya = uşaq maraqlarının nəzərə alınması"),3, "");
//		uploadQuestions(4L,"Nitq qüsurlu uşaqlarla işləyən şəxs ............. nöqtələrin yerinə uyğun gələn variantı seçin ", List.of("A. Tərbiyəçi", "B. Loqoped", "C. Metodist", "D. Dayə"),1, "");
//		uploadQuestions(4L,"Defektoloq kimdir?", List.of("A. Uşaqlara qulluğu həyata keçirən şəxs", "B. Nitq qüsurlu uşaqlarla işləyən şəxs", "C. Uşaqların inkişafını təmin edən şəxs", "D. Uşaqların əqli və fiziki inkişafındakı qüsurların korreksiyası ilə məşğul olan mütəxəssis"),3, "");
//		uploadQuestions(5L,"Uyğunlaşdırın: \n1. Məktəbəqədər təhsilin məqsədi\n2. Məktəbəqədər təhsilin vəzifəsi\n3. Məktəbəqədər təhsilin məzmunu\na. Uşaq şəxsiyyətinin formalaşdırılması\nb. Müstəqillik va məsuliyyət hissinin formalaşdırılması\nc. Məktəbəqədər təhsilin davamlı inkişafını təmin etmək", List.of("A. 1a, 2b, 3c", "B. 1c, 2a, 3b", "C. 1b, 2c, 3a", "D. 1c, 2b, 3a"),1, "");
//		uploadQuestions(5L,"Məktəbəqədər təhsilin məzmununa aid olan variantı seçin.", List.of("A. Təhsilin baza komponentləri əsasında sosiallaşma təlabatının yaradılması", "B. Didaktik prinsiplər", "C. Fiziki, psixi və mənəvi sağlamlığının qorunmasının təmin edilməsi", "D. İnklüziv təhsil xidmətinin göstərilməsi"),1, "");
//		uploadQuestions(5L,"Yoldaşları ilə ünsiyyət qurmaqda çətinlik çəkən Leylada tərbiyyəçi hansı didaktik prinsipi inkişaf etdirməlidir?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Şəxsiyyǝtyönümlülük", "D. İnteqrativlik"),2, "");
//		uploadQuestions(5L,"Uşaqlarda sadədən mürəkkəbə doğru bacarıqları inkişaf etdirən tərbiyəçi hansı didaktik prinsipi reallaşdırır?", List.of("A. Dünyəvilik", "B. Tələbyönümlülük", "C. Azərbaycançılıq", "D. Varislik"),3, "");
//		uploadQuestions(5L,"Sada həndəsi fiqurlar haqqında məşğələ keçən tərbiyəçi müəllim, qrup işi zamanı uşaqlara plastilindan sadə fiqurlar düzəltmək tapşırığını verir. Tapşırığı yerinə yetirən uşaqlarda tərbiyəçi hansı prinsipi inkişaf etdirir?", List.of("A. İnteqrativlik", "B. Uşaqyönümlülük", "C. Şəxsiyyətyönümlülük", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions(5L,"Məşğələ zamanı Anarın nailiyyətini qeyd etməyən tərbiyəçi hansı didaktik prinsipi pozur?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Şəxsiyyətyönümlülük", "D. İnteqrativlik"),1, "");
//		uploadQuestions(5L,"Məşğələ zamanı tərbiyəçi müəllim uşaqlara xalqımızın adət-ənənələri, tarixi və mədəniyyəti haqqında məlumat verir. Tərbiyəçinin inkişaf etdirdiyi didaktik prinsip?", List.of("A. Şəxsiyyǝtyönümlülük", "B. Uşaqyönümlülük", "C. Varislik", "D. Azərbaycançılıq"),3, "");
//		uploadQuestions(5L,"Məktəbəqədər təhsilin məqsədini seçin", List.of("A. Uşaqların milli-mənəvi dəyərlər ruhunda təhsilə hazırlığını təmin etmək", "B. Cəmiyyətə sosial uyğunlaşma", "C. Müşahidə etdiklərinə münasibət bildirmək", "D. Qərar qəbul etmək və müstəqil öyrənmək "),0, "");
//		uploadQuestions(5L,"Tərbiyəçi yol hərəkəti qaydalarını uşaqlara öyrədir, daha sonra həyatda tətbiqini həyata keçirdir. Tərbiyyəçinin uşaqlarda formalaşdırdığı didaktik prinsip?", List.of("A. İnteqrativlik", "B. Varislik", "C. Dünyəvilik", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions(5L,"Tərbiyəçinin verdiyi qrup işi məşğələnin məzmununa aid deyil. Tərbiyəçi hansı didaktik prinsipi pozub?", List.of("A. İnteqrativlik", "B. Dünyəvilik", "C. İnkişafyönümlülük", "D. Varislik"),3, "");
//		uploadQuestions(5L,"Tərbiyəçi Vüsalın işini dəyərləndirmir. Hansı didaktik prinsip pozulub?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. İnteqrativlik", "D. Şəxsiyyətyönümlülük"),1, "");
//		uploadQuestions(5L,"Yaş səviyyəsinin sonunda uşaqlarda uyğun davranışları formalaşdıran müəllim hansı didaktik prinsipi reallaşdırıb?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Naticǝyönümlülük", "D. Şəxsiyyǝtyönümlülük"),2, "");
//		uploadQuestions(5L," - uşağın ehtiyaclarının müəyyən edilməsi və ona müvafiq xidmətlərin göstərilməsi\n - sosial-pedaqoji baxımdan ailənin dəyərli üzvü kimi inkişafının təmin edilməsi\n - İnklüziv təhsil xidmətlərinin göstərilməsi\nQeyd olunan xüsusiyyətlər aiddir?", List.of("A. Məktəbəqədər təhsilin məqsədi", "B. Məktəbəqədər təhsilin məzmunu", "C. Məktəbəqədər təhsilin vəzifəsi", "D. Məktəbəqədər təhsilin hüququ"),2, "");
//		uploadQuestions(5L,"Təşkil olunan məşğələnin əsas məqsədi milli dəyərləri, adət-ənənələri uşaqlara aşılamaqdır. Tərbiyyəçi hansı didaktik prinsipi reallaşdırır?", List.of("A. Şəxsiyyətyönümlülük", "B. Uşaqyönümlülük", "C. Azərbaycançılıq", "D. Dünyəvilik"),0, "");
//		uploadQuestions(5L,"Tərbiyəçi uşaqları qruplara bölərkən onların yaş xüsusiyyətlərini və maraqlarını nəzərə alır. Tərbiyəçi hansı didaktik prinsipə əməl edir?", List.of("A. Uşaqyönümlülük", "B. Şəxsiyyətyönümlülük", "C. İnteqrativlik", "D. İnkişafyönümlülük"),0, "");
//		uploadQuestions(5L,"Tərbiyəçi məşğələnin məzmununa uyğun olaraq uşaqlarda XXI əsr bacarıqlarını formalaşdırır. Tərbiyəçi hansı didaktik prinsipi reallaşdırır? ", List.of("A. Azərbaycançılıq", "B. Varislik", "C. Təlǝbyönümlülük", "D. Dünyəvilik"),2, "");
//		uploadQuestions(5L,"Tərbiyyəçi müəllim məşğələni öz istəklərinə uyğun qurur. Müəllimin pozduğu didaktik prinsip?", List.of("A.Şəxsiyyətyönümlülük", "B. Uşaqyönümlülük", "C. İnkişafyönümlülük", "D. Nəticǝyönümlülük"),1, "");
//		uploadQuestions(5L,"Tərbiyəçi müəllim keçəcəyi məşğələnin planını əvvəlcədən hazırlamır. Hansı didaktik prinsip pozulub? ", List.of("A. İnkişafyönümlülük", "B. Nəticǝyönümlülük", "C. Varislik", "D. Tələbyönümlülük"),1, "");
//		uploadQuestions(5L,"Orxan qrup işi zamanı jest mimikalardan istifadə edərək fikrini səlis çatdıra bilir. Tərbiyəçi Orxanda hansı didaktik prinsipi inkişaf etdirib?", List.of("A. Azərbaycançılıq", "B. Dünyəvilik", "C. İnkişafyönümlülük", "D. Şəxsiyyətyönümlülük"),3, "");
//		uploadQuestions(5L,"Tərbiyəçi müəllim məşğələ zamanı xalqımızın tarixi, mədəniyyəti, adət-ənənələri haqqında uşaqlara məlumat verir, bunları bilməyin vacibliyini vurğulayır. Tərbiyəçinin reallaşdırdığı didaktik prinsip?", List.of("A. Uşaqyönümlülük", "B. İnkişafyönümlülük", "C. Azərbaycançılıq", "D. Varislik"),2, "");
//		uploadQuestions(5L,"Uyğunluq pozulmuşdur:", List.of("A. Şəxsiyyətyönümlülük = idraki bacarıqlar", "B. Varislik = sadədən mürəkkəbə", "C. İnkişafyönümlülük = nailiyyətlərin dəyərləndirilməsi", "D. Dünyəvilik= tariximiz, mədəniyyətimiz"),3, "");
//		uploadQuestions(5L,"Tərbiyəçi məşğələ keçən zaman əyani vəsaitlərdən, iş formalarından və iş üsullarından istifadə etmir. Pozulan didaktik prinsip?", List.of("A. İnkişafyönümlülük", "B. Tələbyönümlülük", "C. Nəticǝyönümlülük", "D. Varislik"),1, "");
//		uploadQuestions(5L,"Tərbiyəçi müəllim uşaqlarda sadədən mürəkkəbə doğru bacarıqları inkişaf etdirirsə hansı didaktik prinsipi reallaşdırır?", List.of("A. Nəticǝyönümlülük", "B. İnkişafyönümlülük", "C. Varislik", "D. Tələbyönümlülük"),2, "");
//		uploadQuestions(5L,"Uyğunluq gözlənilmişdir:", List.of("A. inteqrativlik = məzmun komponentlərinin əlaqələndirilməsi", "B. İnkişafyönümlülük = uşaq maraqları", "C. Tələbyönümlülük = idraki inkişaf", "D. Varislik = dövrün tələbi"),0, "");
//		uploadQuestions(5L," - özünün və digərlərinin hüquqlarına hörmət etmək \n - estetik dünyagörüşü, nitq, təsvir və tərtibat bacarıqları \nQeyd olunan xüsusiyyətlər aiddir?", List.of("A. Məktəbəqədər təhsilin məzmununa", "B. Məktəbəqədər təhsilin hüququna", "C. Məktəbəqədər təhsilin məqsədinə", "D. Məktəbəqədər təhsilin vəzifəsinə"),0, "");
//		uploadQuestions(5L,"Məzmun komponentlərini əlaqələndirərək məşğələ keçən tərbiyəçi uşaqları yüksək səviyyədə təlimə cəlb edir. Tərbiyəçinin reallaşdırdığı didaktik prinsip?", List.of("A. Azərbaycançılıq", "B. Dünyəvilik", "C. İnteqrativlik", "D. Varislik"),2, "");
//		uploadQuestions(5L,"\"Azərbaycan dünyəvi ölkədir, birmənalı şəkildə din dövlətdən ayrıdır\" fikri hansı didaktik prinsipə aiddir?", List.of("A. İnkişafyönümlülük", "B. Dünyəvilik", "C. Uşaqyönümlülük", "D. İnteqrativlik"),1, "");
//		uploadQuestions(5L,"Uyğunsuzluğu müəyyən edin", List.of("A. Məzmun komponentlərinin əlaqələndirilməsi/integrativlik ", "B. Bilik, bacarıq və vərdişlərin inkişafı / İnkişafyönümlülük", "C. Məzmun komponentlərinin tamlığı / azərbaycançılıq", "D. Uşaqların potensial imkanları / Uşaqyönümlülük"),2, "");
//		uploadQuestions(5L,"Gülay şeiri əzbərləyə bilmir. Pozulan didaktik prinsip?", List.of("A. Şəxsiyyətyönümlülük", "B. İnkişafyönümlülük", "C. Nəticəyönümlülük", "D. Uşaqyönümlülük"),0, "");

	}
}
