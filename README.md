# organize-it

## Maxims
- it's better not to give up on code quality
- but when learning it's better to make a bunch of shitty apps than one good

## Roadmap:

	essential
		widoczny progres
			widget projektami z wyswietlonym progresem
			widget z taskami wykonanymi dzisiaj
				dodanie archiwizacji taskow
		filtrowanie drzewa

        zaczac robic codziennie flashcardy przez pol godziny
            mechanizm losowania flashcardow

            
        ogarnac hibernate?
            viktora film najpierw
            https://eventory.cc/event/jdd-2021/space/stream/68537

		code editor
			react
				https://github.com/securingsincity/react-ace
			dopisac parser zeby dzialal z kodem

	nice to have
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
        sposob  za ktory task sie zabrac?
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


## ideas
 	dyskryminator taskow  przycisk ktory jak klikniemy to spycha na koniec listy  do uzywania jak task jest nie az tak wazny, potem mozna go usunac jak po czasie nie bedzie nadal wazny
	przycisk w roocie expand all, tylko itemki z pod roota





## <3 Ref links <3
https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-multipart-and-json-to-dto
https://material-ui.com/components/tree-view/

## Dependencies
sdkman
nvm
https://github.com/nvm-sh/nvm
nodejs
nvm install node # "node" is an alias for the latest version
