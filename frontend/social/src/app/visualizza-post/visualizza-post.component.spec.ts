import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaPostComponent } from './visualizza-post.component';

describe('VisualizzaPostComponent', () => {
  let component: VisualizzaPostComponent;
  let fixture: ComponentFixture<VisualizzaPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzaPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
