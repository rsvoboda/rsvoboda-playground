import {ChangeDetectionStrategy, Component} from '@angular/core';

@Component({
  selector: 'app-root',
  styles: [`
    /** container */
    .container {
      display: flex;
      flex-direction: column;
      overflow: hidden;
      border-collapse: collapse;
    }

    /** headers */
    .headers {
      display: flex;
      margin: 0;
      padding: 0;
      min-height: 70px;
      max-height: 70px;
    }
    .header-group {
      display: flex;
      flex-direction: column;
      border-collapse: collapse;
    }
    .header-line {
      display: flex;
      flex-grow: 1;
      width: 100%;
    }
    .header {
      display: flex;
      width: 150px;
      flex-grow: 1;
      flex-direction: column;
      justify-content: center;
      border: solid 1px black;
      text-align: center;  
    }
    
    /** table */
    .board-container {
      display: flex;
      overflow: hidden;
      min-height: 600px; /* TODO calculate this */
      max-height: 600px;
      background-color: antiquewhite;
      overflow-y: scroll;
    }
    .board-content {
      display: flex;
      overflow-x: scroll;
      height: 100%;
    }
    .board-column-group {
      display: flex;
    }
    .board-column {
      display: flex;
      flex-direction: column;
      align-items: center;
      border-left: 1px black solid;
      border-right: 1px black solid;
      width: 150px;
    }
    
    .item {
      display: flex;
      flex-direction: column;
      border: 1px black solid;
      width: 140px;
      min-height: 80px;
    }
  `],
  template: `
<div style="width: 60%; height: 800px" class="container">
  <div class="headers" [ngStyle]="{'margin-left':boardLeftOffset+'px'}">
    <div class="header-group">
      <div class="header-line">
        <div class="header">Header 1</div>
      </div>
    </div>
    <div class="header-group">
      <div class="header-line">
        <div class="header">Header 2</div>
      </div>
      <div class="header-line">
        <div class="header">State 1</div>
        <div class="header">State 2</div>
      </div>
    </div>
    <div class="header-group">
      <div class="header-line">
        <div class="header">Header 3</div>
      </div>
      <div class="header-line">
        <div class="header">State 3</div>
        <div class="header">State 4</div>
      </div>
    </div>
    <div class="header-group">
      <div class="header-line">
        <div class="header">Header 4</div>
      </div>
    </div>
    <div class="header-group">
      <div class="header-line">
        <div class="header">Header 5</div>
      </div>
    </div>
  </div>
  <div class="board-container">
    <div class="board-content" (scroll)="onScrollTableBodyX($event)">
      <div class="board-column-group">
        <div class="board-column">
          <div class="item">
            <div>Item 1</div>
          </div>
          <div class="item">
            <div>Item 2</div>
          </div>
          <div class="item">
            <div>Item 3</div>
          </div>
          <div class="item">
            <div>Item 4</div>
          </div>
          <div class="item">
            <div>Item 5</div>
          </div>
          <div class="item">
            <div>Item 6</div>
          </div>
          <div class="item">
            <div>Item 4</div>
          </div>
          <div class="item">
            <div>Item 5</div>
          </div>
          <div class="item">
            <div>Item 6</div>
          </div>
          <div class="item">
            <div>Item 6</div>
          </div>
          <div class="item">
            <div>Item 6</div>
          </div>
        </div>
      </div>
      <div class="board-column-group">
        <div class="board-column">
  
        </div>
        <div class="board-column">
  
        </div>
      </div>
      <div class="board-column-group">
        <div class="board-column">
  
        </div>
        <div class="board-column">
  
        </div>
      </div>
      <div class="board-column-group">
        <div class="board-column">
  
        </div>
      </div>
      <div class="board-column-group">
        <div class="board-column">
  
        </div>
      </div>
    </div>
  </div>
</div>
    `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FlexboxComponent {
  boardLeftOffset = 0;

  constructor() {

  }

  onScrollTableBodyX($event: Event) {
    const leftOffset = event.target['scrollLeft'] * -1;
    console.log('--->' + leftOffset);
    if (this.boardLeftOffset != leftOffset) {
      this.boardLeftOffset = leftOffset;
    }
  }

}

