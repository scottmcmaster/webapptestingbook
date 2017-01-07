/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { Weather } from './weather';
import { WeatherSearch } from './weather-search';
import { WeatherService } from './weather.service';
import { WeatherSearchFormComponent } from './weather-search-form.component';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs/Rx';

describe( 'Weather Search Form Component', () => {

    class MockWeatherService {
        weather: Weather;
        doError = false;
        getWeather( city: string, country: string ) : Observable<Weather> {
            if ( this.doError ) {
                return Observable.throw(new Error('oops'));
            }
            return Observable.of( this.weather );
        }
    }

    let mockService;
    let searchForm;
    let fixture;

    beforeEach(() => {
        mockService = new MockWeatherService();
        TestBed.configureTestingModule( {
            providers: [{ provide: WeatherService, useValue: mockService }],
            declarations: [WeatherSearchFormComponent],
            imports: [
                FormsModule
            ]
        });

        TestBed.overrideComponent( WeatherSearchFormComponent, {
            set: {
                providers: [
                    { provide: WeatherService, useValue: mockService }
                ]
            }
        });

        fixture = TestBed.createComponent( WeatherSearchFormComponent );
        searchForm = fixture.componentInstance;
    });

    it( 'does not fetch weather if the model is invalid', () => {
        // ...
        searchForm.model = new WeatherSearch( '', '' );
        searchForm.onSubmit();
        expect( searchForm.weather.cityName ).toEqual( '' );
        expect( searchForm.weather.temperature ).toEqual( '' );
        expect( searchForm.submitted ).toBeFalsy();
        expect( searchForm.errorMessage ).toBeFalsy();
    });

    it( 'should update the weather successfully if the model is valid', () => {
        // ...
        mockService.weather = new Weather( 'beijing', '90', '1000', '30', '12@300' );
        searchForm.model = new WeatherSearch( 'beijing', 'cn' );
        searchForm.onSubmit();
        expect( searchForm.weather.cityName ).toEqual( 'beijing' );
        expect( searchForm.weather.temperature ).toEqual( '30' );
        expect( searchForm.submitted ).toBeTruthy();
        expect( searchForm.errorMessage ).toBeFalsy();
    });

    it( 'sets an error message if fetching the weather fails', () => {
        // ...
        mockService.doError = true;
        searchForm.model = new WeatherSearch( 'beijing', 'cn' );
        searchForm.onSubmit();
        expect( searchForm.errorMessage.message ).toEqual( 'oops' );
        expect( searchForm.submitted ).toBeTruthy();
    });
});
