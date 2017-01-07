/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { WeatherService } from './weather.service';
import { HttpModule } from '@angular/http';

describe('Service: Weather', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WeatherService],
      imports: [
                HttpModule
              ]

    });
  });

  it('should ...', inject([WeatherService], (service: WeatherService) => {
    expect(service).toBeTruthy();
  }));
});
