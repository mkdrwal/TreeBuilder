import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class EnvService {

    constructor() {
    }

    public getApiUrl(path: string) {
        path = path.trim();

        if (path[0] != '/') {
            path = '/' + path;
        }

        return environment.apiUrl + path;
    }
}
