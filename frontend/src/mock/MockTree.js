export default {
  id: '1000',
  note: 'mock Parent',
  children: [
    {
      id: '2',
      note: 'mock Parent',
      children: [
        {
          id: '3',
          note: 'mock Child - 1',
        },
        {
          id: '4',
          note: 'mock Child - 3',
          children: [
            {
              id: '5',
              note: 'mock Child - 4',
            },
          ],
        },
      ],
    },
    {
      id: '6',
      note: 'mock Parent',
      children: [
        {
          id: '7',
          note: 'mock Child - 1',
        },
        {
          id: '8',
          note: 'mock Child - 3',
          children: [
            {
              id: '9',
              note: 'mock Child - 4',
            },
          ],
        },
      ],
    },
    {
      id: '10',
      note: 'mock tooon',
      children: [
        {
          id: '11',
          note: 'mock Child - 21',
        },
        {
          id: '12',
          note: 'mock Child - 33',
          children: [
            {
              id: '13',
              note: 'mock Child - 5',
            },
            {
              id: '14',
              note: 'mock Child - 5',
            },
            {
              id: '15',
              note: 'mock Child - 5',
            },
          ],
        },
      ],
    },
  ],
};
