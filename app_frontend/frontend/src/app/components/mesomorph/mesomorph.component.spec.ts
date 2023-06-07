import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesomorphComponent } from './mesomorph.component';

describe('MesomorphComponent', () => {
  let component: MesomorphComponent;
  let fixture: ComponentFixture<MesomorphComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MesomorphComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MesomorphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
