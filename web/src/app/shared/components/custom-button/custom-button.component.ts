import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-custom-button',
  templateUrl: './custom-button.component.html',
  styleUrls: ['./custom-button.component.scss']
})
export class CustomButtonComponent implements OnInit {
  @Input('title') title: string = 'Enviar';
  @Input('fontSize') fontSize: string = '14';
  @Input('textAlign') textAlign: string = 'center';
  @Input('height') height: string = '30';
  @Input('buttonStyle') buttonStyle:
    'button-default' |
    'button-primary' |
    'button-secondary' |
    'button-info' |
    'button-warning' |
    'button-error' |
    'button-light' |
    'button-dark' = 'button-default';

  @Output('handleExec') handleExec: EventEmitter<any> = new EventEmitter<any>();
  @Input('typeButton') typeButton: 'button' | 'menu' | 'reset' | 'submit' = 'button';

  constructor() { }

  ngOnInit(): void {
  }

  // Para que o botão retorne algum valor é preciso carregar a função no click e o valor no emiter
  handleExecFunc() {
    this.handleExec.emit('Valor');
  }

}
