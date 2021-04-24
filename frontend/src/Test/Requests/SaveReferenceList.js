
export default fetch(new URL('/api/dev/resources', 'http://localhost:8080'), {
    method: 'POST',
    mode: 'cors',
    // headers: {
    //     'Content-Type': "multipart/form-data"
    // },
    headers: {
        'Content-Type': "application/json"
    },
    body: JSON.stringify(
        [
            {
            "@class": "com.zuber.organizeit.Model.ImageRefResource",
            "id": 99,
            "caption": null,
            "comment": null,
            "ref_image": "https://img-9gag-fun.9cache.com/photo/a5WnX5o_700b.jpg"
            },
            {
            "@class": "com.zuber.organizeit.Model.VideoReference",
            "id": 100,
            "caption": null,
            "comment": null,
            "reference_url": "https://www.youtube.com/watch?v=rbIfdWnTMNE&ab_channel=AsfaltRecords"
            },
            {
            "@class": "com.zuber.organizeit.Model.BookReference",
            "id": 101,
            "caption": 'heheasddas',
            "comment": null,
            "author": "Thomas H. Cormen",
            "page": null
            }
        ]    
    )
})