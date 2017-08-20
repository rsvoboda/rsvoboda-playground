import { ReduxDemoPage } from './app.po';

describe('redux-demo App', () => {
  let page: ReduxDemoPage;

  beforeEach(() => {
    page = new ReduxDemoPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
