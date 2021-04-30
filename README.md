# organize-it

## Features:
- dodawanie zadań powtarzających się do google callendara (jedno zadanie które wykonujemy codziennie o jednej porze i mamy obliczone ile nam zajmie czasu),
- obliczanie czasu potrzebnego na zrobienie zadania,
- quick quiz - hyperlink do strony z powtorzeniami, fiszkami,
- podstrona do wprowadzania pytań,
- listy pomysłów e.g. projekty ...
- branche z projektami - np branch programowanie, branch zdrowe_odzywanie/dieta
- listy zadan do wykonania,
- planowanie zadan - trzeba co niedziele planowac i wrzucac recznie zadania do wykonania. Wrzucamy z puli zadan.
- dyskryminator taskow - przycisk ktory jak klikniemy to spycha na koniec listy - do uzywania jak task jest nie az tak wazny, potem mozna go usunac jak po czasie nie bedzie nadal wazny
- przycisk w roocie expand all, tylko itemki z pod roota


## Visualization:
- następny task do wykonania w projekcie - np. link do przeczytania artykulu
- taski wykonane dzisiaj, overview taskow wykonanych w ostatnich dniach
- tabela wszystkich obecnych projektow, krotko i dlugodystansowych
- dodawanie pytan do quizow - drag and drop zdjecie z przykladem, odpowiedz, przycisk do dodawania nowych odpowiedzi testowych, pole do wpisywania pytani, podpowiedzi do dodawania tagu pytania(wazne).


https://material-ui.com/components/tree-view/


## <3 stackoverflow <3
https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-multipart-and-json-to-dto




todo bugs:
- Rootowa klasa nie jest rozpoznawana
  Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Unrecognized field "@class" (class com.zuber.organizeit.Model.Flashcard), not marked as ignorable; nested exception is com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "@class" (class com.zuber.organizeit.Model.Flashcard), not marked as ignorable (5 known properties: "short_answer", "fc_id", "question", "long_answer", "reference_resources"])
 at [Source: (ByteArrayInputStream); line: 1, column: 12] (through reference chain: com.zuber.organizeit.Model.Flashcard["@class"])],
- wysylany formularz musi byc w konwencji cammelCase, chociaz backend wysyla w snake_case

