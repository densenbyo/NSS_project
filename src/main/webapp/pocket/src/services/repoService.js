import axios from "axios";
import {baseUrl} from "./urlService";

class RepoService {
    getAllRepo(){
        return axios.get(`${baseUrl}/repo/all`)
    }

    getCurrentRepo(id){
        return axios.get(`${baseUrl}/repo/` + id, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            }
        })
    }

    getAllCards(id){
        return axios.get(`${baseUrl}/repo/` + id + `/flashcards`, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            }
        })
    }

    getAllDecks(id){
        return axios.get(`${baseUrl}/repo/` + id + `/flashcardDecks`, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            }
        })
    }

    addNewDeck(name, description, isPublic){
        return axios.put(`${baseUrl}/repo/` + localStorage.getItem("userID") + `/flashcardDecks`, {
            name, description, isPublic
        }, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            }
        })
            .then(response => {
                console.log(response.data);
            });
    }

    deleteDeck(id, deckId, name, desc){
        return axios.delete(`${baseUrl}/repo/` + id + `/flashcardDecks`, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            },
            data: {
                id:deckId,
                name:name,
                description:desc
            }
        })
            .then(() => {
                window.location.reload();
            });
    }

    addNewCard(id, word, translation){
        return axios.put(`${baseUrl}/repo/` + id + `/flashcards`, {word,translation},
            {
                headers: {
                    'Authorization' : 'Bearer ' + localStorage.getItem("token")
                }
            })
            .then(response => {
                console.log(response.data);
            });
    }

    deleteCard(id, cardId, word, translation){
        return axios.delete(`${baseUrl}/repo/` + id +`/flashcards`, {
            headers: {
                'Authorization' : 'Bearer ' + localStorage.getItem("token")
            },
            data: {
                id:cardId,
                word:word,
                translation:translation
            }
        })
            .then(() => {
                window.location.reload();
            });
    }
}

export default new RepoService();