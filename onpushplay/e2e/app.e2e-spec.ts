import { OnpushplayPage } from './app.po';

describe('onpushplay App', () => {
  let page: OnpushplayPage;

  beforeEach(() => {
    page = new OnpushplayPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
