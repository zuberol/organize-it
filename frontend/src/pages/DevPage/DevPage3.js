import { useState, useRef } from 'react';
import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';
import React from 'react';
import './../../common/Form/form.css';
// import './../../common/Form/treeStructure.scss';

//todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
export default function FileCheck() {
    const decksNames = useSelector(state =>
        state.flashcardReducer.decks.map(d => d.title)
    );
    const [flashcard, setFlashcard] = useState({
        ref_resource_index: '',
        // ref_resource_associated_files: undefined
        // deckName: decksNames.length > 0 ? decksNames[0] : ''
    });
    const fileRef = useRef(null)


    function handleSubmit(event) {
        event.preventDefault();
        //todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
        
        const form = new FormData()
        
        for (var i = 0; i < fileRef.current.files.length; i++) {
            var file;

            // get item
            file = fileRef.current.files.item(i);
            //or
            file = fileRef.current.files[i];

            console.log(file.name)
            form.append('refResourceAssociatedFiles', file);

        }
        

        form.append('refResourceIndex', flashcard.ref_resource_index);
        // form.append('question', "a srasz?");
        // form.append('shortAnswer', "ajax");


        const fc = {    // todo Flashcard.empty()
            '@class': 'com.zuber.organizeit.Model.Flashcard',
            'question': 'wysylanie fc z ref z plikiem',
            'short_answer': 'shortyyyy',
            'long_answer': 'any longer',
            'reference_resources': [
                {
                    "@class": "com.zuber.organizeit.Model.BookReference",
                    "id": '',
                    "caption": 'test',
                    "comment": 'test comment',
                    "author": "test authon",
                    "title": 'A Great Journey',
                    "page": 'test page'
                }
            ]
        }
        form.append('flashcard', new Blob([JSON.stringify(fc)], {
            type: "application/json"
        }));


        // fileRef.current.files
        // formData.append('flashcard', new Blob([JSON.stringify(flashcard)], {
        //     type: "application/json"
        // }));

        fetch(new URL('/api/dev/filecheck', BACKEND_BASE_URL), {
            method: 'POST',
            mode: 'cors',
            body: form
        })
        .catch((e) => console.error("Błąd przy zapisywaniu flashcarda:", e));
    }

    // function handleFileChange(e) {
    //     e.preventDefault();
    //     setFlashcard({ 
    //         ...flashcard, 
    //         ref_resource_associated_files: e.target.files[0] });
    // };

    function handleChange(e) {
        setFlashcard({
            ...flashcard,
            [e.target.name]: e.target.value
        });
    }

    return (
        <form name="ref_files" onSubmit={handleSubmit}>

            <label htmlFor="ref_resource_index">ref_resource_index</label>
            <input
                name="ref_resource_index"
                type="text"
                onChange={handleChange}
                value={flashcard.ref_resource_index}
            />


            <label htmlFor="ref_resource_associated_files">ref_resource_associated_files</label>
            <input
                name="ref_resource_associated_files"
                type="file"
                // onChange={handleFileChange}
                multiple={true}
                ref={fileRef}
                // value={flashcard.ref_resource_associated_files}
            />

            <button type="submit">Submit</button>
        </form>
    );
};

