import { NumberSymbol } from "@angular/common";

export interface Post{
    id : number;
    titolo : string;
    testo : string;
    creazione: Date;
    aggiornamento: Date;
    numLike : number;
    numDislike : number;
    nicknameOwner : string;
}