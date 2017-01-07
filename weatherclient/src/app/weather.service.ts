import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams } from '@angular/http';

import { Weather } from './weather';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class WeatherService {
    private weatherUrl = 'http://localhost:8080/api/weatherv2/';  // URL to web API

    constructor( private http: Http ) { }
    
    getWeather( city: string, country: string ): Observable<Weather> {
        let params = new URLSearchParams();
        let requestUrl = this.weatherUrl + country + '/' + city;
        return this.http.get( requestUrl )
            .map( this.extractData )
            .catch( this.handleError );
    }
    private extractData( res: Response ) {
        let body = res.json();
        return body || {};
    }

    private handleError( error: Response | any ) {
        let errMsg: string;
        if ( error instanceof Response ) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify( body );
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error( errMsg );
        return Observable.throw( errMsg );
    }
}
