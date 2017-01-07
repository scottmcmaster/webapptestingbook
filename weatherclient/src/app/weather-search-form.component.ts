import { Component } from '@angular/core';
import { Weather } from './weather';
import { WeatherSearch } from './weather-search';
import { WeatherService } from './weather.service';

@Component({
  selector: 'weather-search-form',
  templateUrl: 'weather-search-form.component.html',
  styleUrls: ['./weather-search-form.component.css'],
})

export class WeatherSearchFormComponent {
  model = new WeatherSearch('', '');
  errorMessage: string;
  weather: Weather; 
  submitted = false;
  active = true;
  
  onSubmit() {
      if (!this.model.city || !this.model.country) return;
      this.resetResults();
      
      this.weatherService
          .getWeather(this.model.city, this.model.country)
          .subscribe(
                  weather  => this.weather = weather,
                  err =>  this.handleError(err));
      this.submitted = true;
  }
  
  resetResults() {
      this.weather = new Weather('', '', '', '', '');
      this.errorMessage = '';
  }

  handleError(err: any) {
      this.weather = new Weather('N/A', 'N/A', 'N/A', 'N/A', 'N/A');
      this.errorMessage = err;
  }
  
  constructor (private weatherService: WeatherService) {
      this.weather = new Weather('', '', '', '', '');
  }
}