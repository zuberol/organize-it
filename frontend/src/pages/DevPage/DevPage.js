import { useState } from 'react';
import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';
import React from 'react';
import './../../common/Form/form.css';
// import './../../common/Form/treeStructure.scss';

//todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
export default function AddTaskModal() {
    return (
        <main style={{ display: "flex", "justifyContent": "center", backgroundColor: '#F1E0C5', 'minHeight': '85vh' }}>
            <div style={{ display: "flex", "justifyContent": "center", backgroundColor: "#c9b79c", width: "800px" }}>
                <FlashcardCreator />
            </div>
        </main>
    );





    function FlashcardCreator() {
        const decks = useSelector(state =>
            state.flashcardReducer.decks
        );
        const [flashcard, setFlashcard] = useState({    // todo Flashcard.empty()
            // '@class': 'com.zuber.organizeit.Model.Flashcard',
            'question': 'wysylanie fc z ref z plikiem',
            'short_answer': '',
            'long_answer': '',
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
        });
        const [files, setFiles] = useState(null);
        const [picked_deck_id, setPickedDeckId] = useState(decks.length > 0 ? decks[0].deck_id : '')

        // console.log(flashcard)

        return (
            <form onSubmit={handleSubmit} name="flashcardForm" id="flashcardForm">
                <h1>{flashcard.question}</h1>
                <span className="control-buttons">
                    <button className="control-button" type="submit">Submit</button>
                    <button className="control-button" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.VideoReference")}>+ yy video ref</button>
                    <button className="control-button" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.BookReference")}>+ book ref</button>
                    <button className="control-button" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.ImageReference")}>+ img ref</button>
                </span>
                <fieldset>
                    <label htmlFor="question">Question</label>
                    <input
                        name="question"
                        type="text"
                        onChange={handleChangeBasic}
                        value={flashcard.name}
                    />

                    <label htmlFor="short_answer">Short answer</label>
                    <input
                        name="short_answer"
                        type="text"
                        onChange={handleChangeBasic}
                        value={flashcard.short_answer}
                    />

                    <label htmlFor="long_answer">Long answer</label>
                    <input
                        name="long_answer"
                        type="text"
                        onChange={handleChangeBasic}
                        value={flashcard.long_answer}
                    />
                    <label htmlFor="deckName">Deck name</label>
                    <select
                        id="deck_name"
                        name="deck_name"
                        onChange={(e) => setPickedDeckId(e.target.value)}
                    >
                        {decks.map((deck) => <option key={deck.deck_id} value={deck.deck_id}>{deck.title}</option>)}
                    </select>
                </fieldset>
                <fieldset className="reference_resources">
                    {renderRefResourceInputs(flashcard.reference_resources)}
                </fieldset>
            </form>
        );

        function handleSubmit(event) {
            event.preventDefault();

            console.log(flashcard)


            const formData = new FormData();

            const ref_files = {
                'ref_resource_index': '1',
                'ref_resource_associated_files': files
            }

            // console.log(document.querySelectorAll('.reference_resources fieldset input.file_ref').files)

            // formData.append('file', document.forms["flashcardForm"].file.files[0])
            formData.append('flashcard', new Blob([JSON.stringify(flashcard)], {
                type: "application/json"
            }));

            formData.append("ref_files", ref_files);
            // formData.append('ref_files', new Blob([JSON.stringify(ref_files)], {
            //     type: "application/json"
            // }))

            fetch(new URL('/api/flashcard', BACKEND_BASE_URL), {
                method: 'POST',
                mode: 'cors',
                body: formData
            })
            .catch((e) => console.log("Błąd przy zapisywaniu flashcarda:", e));
        }

        function createRefResource(res_type) {

            let res_def;
            switch (res_type) {
                case 'com.zuber.organizeit.Model.ImageReference': {
                    res_def = {
                        "@class": "com.zuber.organizeit.Model.ImageReference",
                        "id": '',
                        "caption": null,
                        "comment": null,
                        "reference_url": "",
                        // "file": ""
                    }
                }
                    break;
                case 'com.zuber.organizeit.Model.VideoReference':
                    res_def = {
                        "@class": "com.zuber.organizeit.Model.VideoReference",
                        "id": '',
                        "caption": null,
                        "comment": null,
                        "reference_url": ""
                    }

                    break;
                default:
                    res_def = {
                        "@class": "com.zuber.organizeit.Model.BookReference",
                        "id": '',
                        "caption": null,
                        "comment": null,
                        "title": '',
                        "author": "",
                        "page": null
                    }
            }
            setFlashcard({
                ...flashcard,
                reference_resources: [...flashcard.reference_resources, res_def]
            })
        }


        function handleChangeBasic(e) {
            setFlashcard({
                ...flashcard,
                [e.target.name]: e.target.value
            });
        }

        function handleChangeReferenceResource(e, res_index) {
            const updating_resource = {
                ...flashcard.reference_resources[res_index],
                [e.target.name]: e.target.value
            }
            const copy_ref_resources = [...flashcard.reference_resources]
            copy_ref_resources[res_index] = updating_resource;

            setFlashcard({
                ...flashcard,
                'reference_resources': copy_ref_resources
            });
        }

        // function handleFileUpload(e) {

        // }



        function renderRefResourceInputs(reference_resources) {
            return reference_resources.map(res => res['@class']).map((res, i) => {
                switch (res) {
                    case 'com.zuber.organizeit.Model.ImageReference':
                        return <fieldset key={i}>
                            <h6>{`${i}# Image reference`}</h6>
                            <label>Reference url</label>
                            <input
                                type="text"
                                onChange={(e) => handleChangeReferenceResource(e, i)}
                                name="reference_url"
                                value={flashcard.reference_resources[i].reference_url}
                            />
                            <input
                                type="file"
                                onChange={(e) => setFiles(e.target.files)}
                                name="file_ref"
                                value={flashcard.reference_resources[i].reference_url}
                            />
                        </fieldset>
                    case 'com.zuber.organizeit.Model.VideoReference':
                        return <fieldset key={i}>
                            <h6>{`${i}# Video reference`}</h6>
                            <label>Reference url</label>
                            <input
                                type="text"
                                onChange={(e) => handleChangeReferenceResource(e, i)}
                                name="reference_url"
                                value={flashcard.reference_resources[i].reference_url}
                            />
                        </fieldset>
                    case 'com.zuber.organizeit.Model.BookReference':
                        return <fieldset key={i}>
                            <h6>{`${i}# Book reference`}</h6>
                            <label>Book name</label>
                            <input
                                type="text"
                                onChange={(e) => handleChangeReferenceResource(e, i)}
                                name="title"
                                value={flashcard.reference_resources[i].title}
                            />
                            <label>Author</label>
                            <input
                                type="text"
                                onChange={(e) => handleChangeReferenceResource(e, i)}
                                name="author"
                                value={flashcard.reference_resources[i].author}
                            />
                        </fieldset>
                }
            })
        }

    };

}


