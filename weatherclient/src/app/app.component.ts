import { Component } from '@angular/core';
//Add the RxJS Observable operators.
import './rxjs-operators';
import { WeatherService } from './weather.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WeatherService]
})
export class AppComponent {

}
