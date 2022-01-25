# organize-it

## Maxims
- it's better not to give up on code quality
- but when learning it's better to make a bunch of shitty apps than one good
- ? make the hardest one first


## todo
	sprawdzic, czy da sie zapisywac taski
		same
		z podpieciem do stp

## vision
		zmiana taska w pojekcie, to zmiana Task#AggregateRoot, zapisanie przez taskRepo i refresh Project#AggregateRoot
		nie mozna tworzyc repo dla encji nieRootowych!!
		nie mozna tworzyc encji do modyfikacji
			trzeba ustawic za pomoca Task#AggregateRoot.mergeChanges(dto : TaskDownstreamObject <- some Json, anticorruption layer object)

## ideas
 	dyskryminator taskow  przycisk ktory jak klikniemy to spycha na koniec listy  do uzywania jak task jest nie az tak wazny, potem mozna go usunac jak po czasie nie bedzie nadal wazny
	przycisk w roocie expand all, tylko itemki z pod roota
	nie powinno byc widoku w ktorym jest za duzo taskow, zeby nie bylo to przytlaczajace

	jak sie wchodzi na mount everest to sie nie widzi szczytu
		mozna powiedziec tylko, czy sie idzie w dobrym kierunku
			dobry kierynek to w gore
				mozna wybrac droge dluzsza latwiejsza (mniej wysilkowa) albo szybsza ale trudniejsza (wiecej wysilkowa)
					ale jedno i drugie cie doprowadzi do celu
						wolimy latwiejsze rzeczy, bo nie lubimy sie wysilac
							chociaz czasami nie ma sie wyboru i trzeba wybrac sie trudniejsza trasa
							koniec koncow trzeba wybrac droge ktora doprowadzi cie do celu
								nawet jak wiecej satysfakcji ci sprawia trudniejsza droga to nie o to chodzi
									pytanie nie bylo co ci sprawia wieksza przyjemnosc? tylko jak dojsc do celu?
									chociaz frajda z drogi koniec koncow jest tez wazna, trzeba o tym pamietac
									ale jak wybierasz trudniejsza droge i nie dojdziesz do celu to to nie bedzie spojne
									bo ty chcesz sie dostac do tego miejsca do ktorego idziesz
										chyba, ze ci nie zalezy? chcesz tylko isc i zeby ci bylo przyjemnie podczas wedrowki

		wieksze taski mozna przyporzadkowac do idei
		np.
				napisalem testy w JUnit dla mojej prostej aplikacji
					trzeba byc szczerym i umiec powiedziec, ze ta aplikaja jest prosta, a nie trudna
				|
				|-> 
				nauczyc sie programowac, ale po co?
					zeby napisac aplikacje webowa
						- napisac prosta aplikacje
						- napisac prosta aplikacje ktora bedzie produkcyjna
						- napisac lepsza aplikacje zeby byla produkcyjna

		mozna mierzyc jak czesto sie dekomponowalo zadania
		taski



		przeniesc Task do oddzielnej paczki i uniezaleznic od TaskDTO
			Task nie moze wiedziec o istnieniu TaskDTO
				dopisac task service, ktory bedzie wysylal wysylal DTO
				task musi byc niezalezny od wszystkiego, natomiast reszta moze byc zalezna od taska












## inbox
    co to jest spliterator i jak tego uzywac, do czego?
    jak dzialac na streamach zeby dobry performance byl?
        o co chodzi z 
            short circut optimization?
            tail optimizationem
            porownac mozna bytecode

		sa zadania upierdliwe+łatwe
			nie powinno byc za nie duzo punktow

# info inbox

	research day
		StringTokenizer do wydobycia tagow
		Spliterators jak dzialaja
		Jave Event model poczytac
		Java concurency
		Java 17
			jak robic currying w Javie 17?
			uzyc recordow
				jak tworzyc factory methody, zeby tylko zmienic jedna rzecz w nich
			sealed class
				sealed ma Permits cluse
					jak w tym samym pliku to mozna pominac
					klasy implementujace sealed musza byc z tej samej paczki
						jak sealed jest w Java Module to musi byc w tym samym module
			switchy z sealed classami
		java 9
			named module co to i po co

		lambda vs static method ref
			lambda 
				bedzie miala generica co klasa ja opakowujaca
				mozna laczyc lambdy z pomoca compose()/andThen(), static method nie da sie tak stackowac
			vs
			static method reference
				ma swojego generica

			to
				private static <E extends BaseEntity<Long>> YmlTreeParser <E>
			vs to
				 private Function<YmlTreeParser<E>, YmlTreeParser<E>> parse







## Roadmap:

	parser
		dla http:wp.pl ma przeparsowac na link
			najpierw parsuj tagi, jak nie ma tagow to zrob cos z linia
				ale co?
		narazie wspolna pula tag <->encja z factory


		nie pozwalam na razie na kod wrzucony w pliku




	taski do zrobienia
		progres taskow
		podpinanie root taskow pod project

	project -> plans
		frontend ogarnac




	TOP tylko to najwazniejsze w chuj
		OLAC kompletnie DDD, zrobic to w chuj nieoptymalnie, ma dzialac dobrze i tyle, bo sie da
			to blokuje nauke, z nauka bedzie o 1000 razy szybciej, to jest crucial

	essential
		modyfikacja taska i planu
			problem nadpisania nullem
				nie wystapi, bo uzywam DownstreamObjectow do modyfikacji encji sledzonych przez entity ctx
		aggregate root plan
			widget wyswietlanie Statow
				Staty
					wyliczanie statow
			AR task
				event listenery do komunikacji miedzy nimi
				powiadamianie od taska do planu, zeby sie zrefreshowal



		// throws stack overflow
	    @GetMapping("/cycle") 
	    Task getCycleTaskTest() {
	        Task one = new Task("one");
	        Task two = new Task("two");
	        one.setSubTasks(List.of(two));
	        two.setSubTasks(List.of(one));
	        return one;
	    }

		vim Notes
			scrapowanie artykulow do krotkiej notatki
			kod examples w oddzielnych plikach
			meta-tagi w pliku z notatka
			directory to cala notatka?
		
			subnotatki?

		tworzenie notatek z VIMa
			link do calej notatki albo do node'a
			wstrzykiwanie CodeRefa
			Folder notatek i wszystko co w nim jest
			rozroznienie plikow po nazwach "flashcards, notes, .java"


		widoczny progres
			widget projektami z wyswietlonym progresem
			widget z taskami wykonanymi dzisiaj
				dodanie archiwizacji taskow

		filtrowanie drzewa
			backend bedzie wysylal indexy taskow w odpowiedzi na filtr+filtr+filtr get
      frontend bedzie pokazywal taski tylko te co dostanie indexy
				nie takie fajne ale zadziala narazie
		zaczac robic codziennie flashcardy przez pol godziny
        jak tworzyc flashcardy łatwo
					jakiegos DSLa musze robic?
						w scali
						bo np code resource jak wgrac?
					musze sie zapisywac na dysk
					musza sie ladowac do apki na startupie z dysku
					
        ogarnac hibernate?
				viktora film najpierw
            https://eventory.cc/event/jdd-2021/space/stream/68537

    priority
        nowe taski na szczycie listy
        dodane priority pushuje do gory listy
            dodanie przycisku + zeby podbic priority
            dodac priority na backendzie


	nice to have

		architektura oparta o DDD
			DomainEventy i EventHandlery w application layerze
				wywalenie hibernate z Encji Task i innych
    dodac logowanie bledow
		dodac strip() w parserze jak ustawia nazwe taska
		dodawanie pytan do quizow 
			formularz
				propertisy tekstowe taska
				lista tagow
				ref resource
					image
						drag and drop zdjecie z przykladem
		architektura oparta o immutable entities
			doczytac czy warto rozdzielic architeture na: DomainEntity (immutable), JPA/Hibernate, jacksonDTO
                chyba nie trzeba, bo tak Viktor powiedzial
                    https://stackoverflow.com/questions/42299341/recommended-strategy-to-use-value-objects-for-ids-in-spring-data
				rozdzieliloby to concerns'y
				dostep do DomainEntity bylby przez jakas fasade wtedy, ktora laczylaby te 3 swiaty
					w apce uzywalbym DomainEntity, wysylal jacksonDTO
            rozdzielic na taski male
                dodac embeddedId w oddzielnym/odseparowanym projekcie
                    podejrzec jak ktos implementuje DDD tym patternem
			ogarniecie lomboka
				dodanie lomboka
					wywalenie lomboka
						z klas ktore obecnie uzywam
						z reszty klas
            przerobienie drzewa na graf
                taski z roznych projektow powinny moc byc od siebie zalezne 
                    np. sprzedanie fotela i zarobienie hajsu moze odblokowac task kupienia nowego snowboardu	
            obliczanie czasu potrzebnego na zrobienie zadania,
        sposob za ktory task sie zabrac?
            zabierac sie za to co mam ochote?
             ktore taski trzeba zrobic zeby najwiecej zyskac?
                ktory task jest wazniejszy?
                    potrzebne rozroznienie, statystyka, priority pointsy
                        -np.
                            ile taskow ten task odblokuje? albo w ilu uczestniczy? 
                            punkty za kazdy task?
                                jak przyznawac punkty?
                                    minor, medium, hard -> 1, 2, 5

    minor
			JS DOCKS EXT
			BUG forwardRef w Projects Page na przyciskach edit task
			redux jest polepiony na duckTape'a, np. inbox widget
				infinite loop jak nie ma taska z name "inbox"

        api do zliczania treningow biegowych
        tool
            react
                wyswietlanie description aktywnego taska
                    widget z focus view taska/projektu  wyswietla sie description, zmienia sie background image

    marginal
        dodawanie zadań powtarzających się do google callendara (jedno zadanie które wykonujemy codziennie o jednej porze i mamy obliczone ile nam zajmie czasu),
        podstrona do wprowadzania pytań,
        planowanie zadan  trzeba co niedziele planowac i wrzucac recznie zadania do wykonania. Wrzucamy z puli zadan.
        widget z lista z taskami do wykonania ( liscie drzewa )
            posortowane po high level task
            
        tool
            react
                widget z nastepnym taskiem do wykonania

		
		dopisac paragraph do NoteCtx

		rzucanie wyjatkow w streamie
			czy runtime exception przerwie stream, pewnie ta


## <3 Ref links <3
https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-multipart-and-json-to-dto
https://material-ui.com/components/tree-view/

## Dependencies
sdkman
nvm
https://github.com/nvm-sh/nvm
nodejs
nvm install node # "node" is an alias for the latest version
