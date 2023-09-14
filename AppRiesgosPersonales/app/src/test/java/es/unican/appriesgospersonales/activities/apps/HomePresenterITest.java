package es.unican.appriesgospersonales.activities.apps;

import static org.mockito.Mockito.verify;

import android.os.Build;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.unican.appriegospersonales.activities.apps.HomePresenter;
import es.unican.appriegospersonales.activities.apps.IHomeContract;
import es.unican.appriegospersonales.common.MyApplication;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1}, application = MyApplication.class)
public class HomePresenterITest {

    private HomePresenter sut;

    @Mock
    private IHomeContract.View viewMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new HomePresenter(viewMock);
    }

//    @Test
//    public void testInitCorrecto() {
//        // IAP.1a
//        when(viewMock.getMyApplication()).thenReturn(ApplicationProvider.getApplicationContext());
//
//        sut.init();
//        verify(viewMock).showLoadCorrect(0);
//    }
}
