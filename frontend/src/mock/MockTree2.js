export default {
  "task_id": 1,
  "note": "nauczyc sie AWS",
  "parentTask": null,
  "sub_tasks": [
    {
      "@class": "com.zuber.organizeit.Model.Task",
      "task_id": 2,
      "note": "zdeplojowac przykladowa apke w AWSie",
      "parentTask": 1,
      "sub_tasks": [
        {
          "@class": "com.zuber.organizeit.Model.Task",
          "task_id": 4,
          "note": "ogarnac EC2",
          "parentTask": 2,
          "sub_tasks": []
        },
        {
          "@class": "com.zuber.organizeit.Model.Task",
          "task_id": 5,
          "note": "ogarnac role",
          "parentTask": 2,
          "sub_tasks": []
        },
        {
          "@class": "com.zuber.organizeit.Model.Task",
          "task_id": 6,
          "note": "ogarnac code pipeline",
          "parentTask": 2,
          "sub_tasks": [
            {
              "@class": "com.zuber.organizeit.Model.Task",
              "task_id": 8,
              "note": "zjesc obiad",
              "parentTask": 6,
              "sub_tasks": []
            },
            {
              "@class": "com.zuber.organizeit.Model.Task",
              "task_id": 9,
              "note": "zjesc sniadanie",
              "parentTask": 6,
              "sub_tasks": []
            }
          ]
        }
      ]
    },
    {
      "@class": "com.zuber.organizeit.Model.Task",
      "task_id": 3,
      "note": "zrobic bigos",
      "parentTask": 1,
      "sub_tasks": []
    },
    {
      "@class": "com.zuber.organizeit.Model.Task",
      "task_id": 7,
      "note": "jedzenie",
      "parentTask": 1,
      "sub_tasks": []
    }
  ]
}