export default {
    id: '1000',
    name: 'Parent',
    children: [
      {
        id: '2',
        name: 'Parent',
        children: [
          {
            id: '3',
            name: 'Child - 1',
          },
          {
            id: '4',
            name: 'Child - 3',
            children: [
              {
                id: '5',
                name: 'Child - 4',
              },
            ],
          },
        ],
      },
      {
        id: '6',
        name: 'Parent',
        children: [
          {
            id: '7',
            name: 'Child - 1',
          },
          {
            id: '8',
            name: 'Child - 3',
            children: [
              {
                id: '9',
                name: 'Child - 4',
              },
            ],
          },
        ],
      },
      {
        id: '10',
        name: 'tooon',
        children: [
          {
            id: '11',
            name: 'Child - 21',
          },
          {
            id: '12',
            name: 'Child - 33',
            children: [
              {
                id: '13',
                name: 'Child - 5',
              },
              {
                id: '14',
                name: 'Child - 5',
              },
              {
                id: '15',
                name: 'Child - 5',
              },
            ],
          },
        ],
      },
    ],
  };
  