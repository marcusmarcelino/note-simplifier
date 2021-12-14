import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalStandbyComponent } from './modal-standby.component';

describe('ModalStandbyComponent', () => {
  let component: ModalStandbyComponent;
  let fixture: ComponentFixture<ModalStandbyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalStandbyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalStandbyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
