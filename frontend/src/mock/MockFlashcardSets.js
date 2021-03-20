import FlashcardSetSimple from './../Model/FlashcardSet';

export default MOCKFLASHCARDSETS = [
  {
    id: 100,
    name: 'java fundamentals',
    flashcards: [
      {
        "question": "RDBMS",
        "page": "",
        "short answer": "",
        "long answer": "Relational Database Management System",
        "ref_url": "",
        "code_sample_url": "",
        "picture_url": "",
        id: 1001
      },
      {
        "question": "JDBC API",
        "page": "",
        "short answer": "",
        "long answer": "application-to-JDBC Manager connection",
        "ref_url": "https://www.tutorialspoint.com/jdbc/jdbc-introduction.htm",
        "code_sample_url": "",
        "picture_url": "",
        id: 1002
      },
      {
        "question": "JDBC Driver API",
        "page": "",
        "short answer": "",
        "long answer": "JDBC Manager-to-Driver Connection",
        "ref_url": "",
        "code_sample_url": "",
        "picture_url": "",
        id: 1003
      }
    ]
  },
  {
    id: 200,
    name: 'java 2',
    flashcards: [
      {
        "question": "Jak zmapowac Authorities z oAuth2.0 providera?",
        "page": "",
        "short answer": "",
        "long answer": "",
        "ref_url": "https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#oauth2login-advanced-map-authorities-grantedauthoritiesmapper",
        "code_sample_url": "https://medium.com/@bvulaj/mapping-your-users-and-roles-with-spring-boot-oauth2-a7ac3bbe8e7f",
        "picture_url": "",
        id: 1004
      },
      {
        "question": "Co robią @RegisteredOAuth2AuthorizedClient(\"spotify\") OAuth2AuthorizedClient authorizedClient,\n@AuthenticationPrincipal OAuth2User oauth2User i czym sie roznia?",
        "page": "",
        "short answer": "",
        "long answer": "",
        "ref_url": "",
        "code_sample_url": "",
        "picture_url": "",
        id: 1005
      }
    ]
  },
  {
    id: 300,
    name: 'spring',
    flashcards: [
      {
        "question": "Co pozytywnego wynika z Anonymous Authentication w Springu?",
        "page": "",
        "short answer": "",
        "long answer": "W SecurityContextHolderze trzymany jest dzieki temu obiekt Authentication, a nie null. Dzieki temu nie będzie NullPointerException jak będziemy chcieli go użyć.",
        "ref_url": "https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#anonymous",
        "code_sample_url": "",
        "picture_url": "",
        id: 1006
      },
      {
        "question": "Po co jest Access-Control-Allow-Credentials header?",
        "page": "",
        "short answer": "",
        "long answer": "Mówi, czy pozwala na wysyłanie w requestach ciasteczek z innych Originów, niż do tego,  z którym obecnie gadamy.",
        "ref_url": "",
        "code_sample_url": "",
        "picture_url": "",
        id: 1007
      },
      {
        "question": "Dlaczego u Matta nie trzeba bylo robic @cross origin (allowCredentials = \"true\"), zeby bylo wysylane JSESSION?",
        "page": "",
        "short answer": "",
        "long answer": "",
        "ref_url": "https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot#fix-redirect-after-login-in-prod-profile",
        "code_sample_url": "",
        "picture_url": "",
        id: 1008
      }
    ]
  }
].map(item => new FlashcardSetSimple(item.id, item.name, item.flashcards));