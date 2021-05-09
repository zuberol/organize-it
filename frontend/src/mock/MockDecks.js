import Deck from '../Model/Deck';

export default [
  {
    deck_id: 100,
    title: 'java fundamentals',
    flashcards: [
      {
        "question": "RDBMS",
        "page": "",
        "short_answer": "",
        "long_answer": "Relational Database Management System",
        "reference_resources": [],
        "fc_id": 1001
      },
      {
        "question": "JDBC API",
        "page": "",
        "short_answer": "",
        "long_answer": "application-to-JDBC Manager connection",
        "reference_resources": [
          {
            "@class": "com.zuber.organizeit.Model.ArticleResource",
            "id": 1,
            "caption": null,
            "comment": null,
            "ref_url": "https://www.tutorialspoint.com/jdbc/jdbc-introduction.htm"
          }
        ],
        "fc_id": 1002
      },
      {
        "question": "JDBC Driver API",
        "page": "",
        "short_answer": "",
        "long_answer": "JDBC Manager-to-Driver Connection",
        "reference_resources": [],
        "fc_id": 1003
      }
    ]
  },
  {
    deck_id: 200,
    title: 'java 2',
    flashcards: [
      {
        "question": "Jak zmapowac Authorities z oAuth2.0 providera?",
        "page": "",
        "short_answer": "",
        "long_answer": "",
        "reference_resources": [
          {
            "@class": "com.zuber.organizeit.Model.ArticleResource",
            "id": 1,
            "caption": null,
            "comment": null,
            "ref_url": "https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#oauth2login-advanced-map-authorities-grantedauthoritiesmapper"
          },
          {
            "@class": "com.zuber.organizeit.Model.ArticleResource",
            "id": 2,
            "caption": null,
            "comment": null,
            "ref_url": "https://medium.com/@bvulaj/mapping-your-users-and-roles-with-spring"
          }
        ],
        "fc_id": 1004
      },
      {
        "question": "Co robią @RegisteredOAuth2AuthorizedClient(\"spotify\") OAuth2AuthorizedClient authorizedClient,\n@AuthenticationPrincipal OAuth2User oauth2User i czym sie roznia?",
        "page": "",
        "short_answer": "",
        "long_answer": "",
        "reference_resources": [],
        "fc_id": 1005
      }
    ]
  },
  {
    deck_id: 300,
    title: 'spring',
    flashcards: [
      {
        "question": "Co pozytywnego wynika z Anonymous Authentication w Springu?",
        "page": "",
        "short_answer": "",
        "long_answer": "W SecurityContextHolderze trzymany jest dzieki temu obiekt Authentication, a nie null. Dzieki temu nie będzie NullPointerException jak będziemy chcieli go użyć.",
        "reference_resources": [
          {
            "@class": "com.zuber.organizeit.Model.ArticleResource",
            "id": 1,
            "caption": null,
            "comment": null,
            "ref_url": "https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#anonymous"
          }
        ],
        "fc_id": 1006
      },
      {
        "question": "Po co jest Access-Control-Allow-Credentials header?",
        "page": "",
        "short_answer": "",
        "long_answer": "Mówi, czy pozwala na wysyłanie w requestach ciasteczek z innych Originów, niż do tego,  z którym obecnie gadamy.",
        "reference_resources": [],
        "fc_id": 1007
      },
      {
        "question": "Dlaczego u Matta nie trzeba bylo robic @cross origin (allowCredentials = \"true\"), zeby bylo wysylane JSESSION?",
        "page": "",
        "short_answer": "",
        "long_answer": "",
        "reference_resources": [
          {
            "@class": "com.zuber.organizeit.Model.ArticleResource",
            "id": 1,
            "caption": null,
            "comment": null,
            "ref_url": "https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot#fix-redirect-after-login-in-prod-profile"
          }
        ],
        "fc_id": 1008
      }
    ]
  }
].map(item => new Deck(item.deck_id, item.title, item.flashcards));