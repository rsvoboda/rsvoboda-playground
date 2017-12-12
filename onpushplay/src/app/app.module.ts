import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {AppComponent, HeaderComponent, HeadersComponent, StateComponent, ItemComponent } from './app.component';
import {FlexboxComponent} from './flexbox.component';

@NgModule({
  declarations: [
      AppComponent,
      FlexboxComponent,      
      HeaderComponent,
      HeadersComponent,
      ItemComponent,
      StateComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [FlexboxComponent]
})
export class AppModule { }
